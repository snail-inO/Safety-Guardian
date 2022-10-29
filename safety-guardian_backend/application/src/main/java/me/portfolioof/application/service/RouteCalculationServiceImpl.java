package me.portfolioof.application.service;

import me.portfolioof.application.entity.Path;
import org.springframework.stereotype.Service;

public class RouteCalculationServiceImpl implements RouteCalculationService{

    private RouteCalculationStrategy routeCalculationStrategy;

    public RouteCalculationServiceImpl(RouteCalculationStrategy routeCalculationStrategy) {
        this.routeCalculationStrategy = routeCalculationStrategy;
    }

    @Override
    public void setStrategy(RouteCalculationStrategy strategy) {
        routeCalculationStrategy = strategy;
    }

    @Override
    public Path calculate(double[] from, double[] to) {
        return routeCalculationStrategy.calculate(from, to);
    }
}
