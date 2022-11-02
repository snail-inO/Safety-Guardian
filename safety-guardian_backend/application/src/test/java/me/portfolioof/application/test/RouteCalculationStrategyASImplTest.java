package me.portfolioof.application.test;

import me.portfolioof.application.entity.Path;
import me.portfolioof.application.service.RiskAssessmentService;
import me.portfolioof.application.service.RiskAssessmentServiceImpl;
import me.portfolioof.application.service.RouteCalculationStrategy;
import me.portfolioof.application.service.RouteCalculationStrategyASImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class RouteCalculationStrategyASImplTest {
    @Autowired
    RiskAssessmentServiceImpl riskAssessmentService;

    @Test
    public void testCalculate() {
        RouteCalculationStrategy as = new RouteCalculationStrategyASImpl(riskAssessmentService);
        double[] from = new double[]{43.04797, -76.13484};
        double[] to = new double[]{43.2340, -76.3087};
        Path path = as.calculate(from, to);
        System.out.println(path.getTotalRisk());
        path.getWaypoints().forEach(stop -> System.out.println(stop[0] + ", " + stop[1]));
    }
}
