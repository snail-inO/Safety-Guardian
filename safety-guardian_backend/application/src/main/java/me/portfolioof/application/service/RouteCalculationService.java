package me.portfolioof.application.service;

import me.portfolioof.application.entity.Path;


public interface RouteCalculationService {
    void setStrategy(RouteCalculationStrategy strategy);
    Path calculate(double[] from, double[] to);
}
