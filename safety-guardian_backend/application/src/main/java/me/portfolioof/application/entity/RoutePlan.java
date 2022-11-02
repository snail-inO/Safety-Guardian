package me.portfolioof.application.entity;

public class RoutePlan {
    private double[] from;
    private double[] to;

    public RoutePlan(double[] from, double[] to) {
        this.from = from;
        this.to = to;
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
}
