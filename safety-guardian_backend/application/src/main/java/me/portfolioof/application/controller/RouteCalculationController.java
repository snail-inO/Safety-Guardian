package me.portfolioof.application.controller;

import me.portfolioof.application.assembler.PathEntityAssembler;
import me.portfolioof.application.entity.Path;
import me.portfolioof.application.service.RouteCalculationService;
import me.portfolioof.application.service.RouteCalculationServiceImpl;
import me.portfolioof.application.service.RouteCalculationStrategyASImpl;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/route")
public class RouteCalculationController {
    private final RouteCalculationService routeCalculationService;
    private final PathEntityAssembler assembler;

    public RouteCalculationController(RouteCalculationStrategyASImpl routeCalculationStrategyAS, PathEntityAssembler assembler) {
        this.routeCalculationService = new RouteCalculationServiceImpl(routeCalculationStrategyAS);
        this.assembler = assembler;
    }

    @GetMapping("/calculate")
    public EntityModel<Path> getPath(@RequestParam double[] from, @RequestParam double[] to) {
        return assembler.toModel(routeCalculationService.calculate(from, to));
    }

}
