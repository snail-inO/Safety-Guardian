package me.portfolioof.application.assembler;

import me.portfolioof.application.controller.UserController;
import me.portfolioof.application.entity.RiskAssessment;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class RiskAssessmentEntityAssembler implements RepresentationModelAssembler<RiskAssessment, EntityModel<RiskAssessment>> {
    @Override
    public EntityModel<RiskAssessment> toModel(RiskAssessment entity) {
        return EntityModel.of(entity,
                linkTo(methodOn(UserController.class).assessRisk(entity.getUid())).withSelfRel(),
                linkTo(methodOn(UserController.class).retrieveUser(entity.getUid())).withRel("retrieveUser"));
    }
}
