package me.portfolioof.application.service;

import me.portfolioof.application.entity.Path;


public interface RouteCalculationStrategy {
    Path calculate(double[] from, double[] to);

}
