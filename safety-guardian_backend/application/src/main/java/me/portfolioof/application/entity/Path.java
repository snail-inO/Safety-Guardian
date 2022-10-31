package me.portfolioof.application.entity;

import java.util.List;

public class Path {
    private double[] from;
    private double[] to;
    private List<double[]> waypoints;
    private int totalRisk;

    public Path(double[] from, double[] to, List<double[]> waypoints, int totalRisk) {
        this.from = from;
        this.to = to;
        this.waypoints = waypoints;
        this.totalRisk = totalRisk;
    }

    public Path(List<double[]> waypoints, int totalRisk) {
        this.waypoints = waypoints;
        this.totalRisk = totalRisk;
    }

    public double[] getFrom() {
        return from;
    }

    public void setFrom(double[] from) {
        this.from = from;
    }

    public double[] getTo() {
        return to;
    }

    public void setTo(double[] to) {
        this.to = to;
    }

    public List<double[]> getWaypoints() {
        return waypoints;
    }

    public void setWaypoints(List<double[]> waypoints) {
        this.waypoints = waypoints;
    }

    public int getTotalRisk() {
        return totalRisk;
    }

    public void setTotalRisk(int totalRisk) {
        this.totalRisk = totalRisk;
    }
}
