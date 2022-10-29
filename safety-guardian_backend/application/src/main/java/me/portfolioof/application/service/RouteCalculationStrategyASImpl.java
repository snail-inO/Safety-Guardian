package me.portfolioof.application.service;

import me.portfolioof.application.entity.Path;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.*;

@Component
public class RouteCalculationStrategyASImpl implements RouteCalculationStrategy{
    private final static double STEP = 0.05;
    private final static double[] LAT_BOUND = new double[]{42.5, 43.5};
    private final static double[] LNG_BOUND = new double[]{-76.5, -75.5};

    private final RiskAssessmentService riskAssessmentService;

    public RouteCalculationStrategyASImpl(RiskAssessmentService riskAssessmentService) {
        this.riskAssessmentService = riskAssessmentService;
    }

    @Override
    public Path calculate(double[] from, double[] to) {
        boolean[][] explored = new boolean[200][200];

        PriorityQueue<State> pq = new PriorityQueue<>(Comparator.comparingInt(State::getRisk));
        int r = calculateHeuristic(from, to) + 0;
        State init = new State(null, from[0], from[1], r, 0, 0);
        pq.offer(init);
        setVisited(explored, init);
        int[] actions = new int[]{0, -1, 0, 1, 0};

        while (!pq.isEmpty()) {
            State cur = pq.poll();
//            System.out.printf("x, y: (%f, %f), risk: %d, cost: %d\n", cur.getX(), cur.getY(), cur.getRisk(), cur.getCost());
            if (calculateDistance(cur.getCoord(), to) < STEP) {
                Path path = getPath(cur);
                path.setFrom(from);
                path.setTo(to);
                return path;
            }

            for (int i = 0; i < actions.length - 1; i++) {
                State next = new State(cur, cur.getX() + actions[i] * STEP, cur.getY() + actions[i + 1] * STEP);
                if (next.getX() < LAT_BOUND[0] || next.getY() < LNG_BOUND[0] || next.getX() > LAT_BOUND[1] || next.getY() > LNG_BOUND[1]) {
                    continue;
                }
                if (isVisited(explored, next)) {
                    continue;
                }
                next.setCurRisk(calculateStepCost(next.getCoord()));
                next.setCost(cur.getCost() + 1 + next.getCurRisk());
                next.setRisk(next.getCost() + calculateHeuristic(next.getCoord(), to));
                pq.offer(next);
                setVisited(explored, next);
            }
        }
        return null;
    }

    private Path getPath(State state) {
        LinkedList<double[]> path = new LinkedList<>();
        int totalRisk = 0;
        while (state.getFrom() != null) {
            totalRisk += state.getCurRisk();
            path.addFirst(state.getCoord());
            state = state.getFrom();
        }

        return new Path(new ArrayList<>(path), totalRisk);
    }
    private void setVisited(boolean[][] explored, State state) {
        int x = (int) ((state.getX() - LAT_BOUND[0]) / STEP);
        int y = (int) ((state.getY() - LNG_BOUND[0]) / STEP);
        explored[x][y] = true;
    }

    private boolean isVisited(boolean[][] explored, State state) {
        int x = (int) ((state.getX() - LAT_BOUND[0]) / STEP);
        int y = (int) ((state.getY() - LNG_BOUND[0]) / STEP);
        return explored[x][y];
    }

    private int calculateStepCost(double[] to) {
        double risk = riskAssessmentService.assess(to[1], to[0], STEP / 2).getRiskLevel().getVal();
        return (int) risk;
    }

    private int calculateHeuristic(double[] from, double[] goal) {
        return (int) (calculateDistance(from, goal) / STEP);
    }

    private double calculateDistance(double[] from, double[] to) {
        return Math.abs(to[0] - from[0]) + Math.abs(to[1] - from[1]);
    }

    private class State {
        private State from;
        private double x;
        private double y;
        private int risk;
        private int cost;
        private int curRisk;

        public State(State from, double x, double y) {
            this.from = from;
            this.x = x;
            this.y = y;
        }

        public State(State from, double x, double y, int risk, int cost, int curRisk) {
            this.from = from;
            this.x = x;
            this.y = y;
            this.risk = risk;
            this.cost = cost;
            this.curRisk = curRisk;
        }

        public State getFrom() {
            return from;
        }

        public void setFrom(State from) {
            this.from = from;
        }

        public double getX() {
            return x;
        }

        public void setX(double x) {
            this.x = x;
        }

        public double[] getCoord() {
            return new double[]{x, y};
        }

        public double getY() {
            return y;
        }

        public void setY(double y) {
            this.y = y;
        }

        public int getRisk() {
            return risk;
        }

        public void setRisk(int risk) {
            this.risk = risk;
        }

        public int getCost() {
            return cost;
        }

        public void setCost(int cost) {
            this.cost = cost;
        }

        public int getCurRisk() {
            return curRisk;
        }

        public void setCurRisk(int curRisk) {
            this.curRisk = curRisk;
        }
    }
}
