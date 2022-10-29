package me.portfolioof.application.assembler;

import me.portfolioof.application.controller.RouteCalculationController;
import me.portfolioof.application.entity.Path;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class PathEntityAssembler implements RepresentationModelAssembler<Path, EntityModel<Path>> {
    @Override
    public EntityModel<Path> toModel(Path entity) {
        return EntityModel.of(entity, linkTo(methodOn(RouteCalculationController.class).getPath(entity.getFrom(), entity.getTo())).withSelfRel());
    }
}
