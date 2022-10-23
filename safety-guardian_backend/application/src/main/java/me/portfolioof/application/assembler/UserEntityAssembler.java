package me.portfolioof.application.assembler;

import me.portfolioof.application.controller.UserController;
import me.portfolioof.application.entity.User;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class UserEntityAssembler implements RepresentationModelAssembler<User, EntityModel<User>> {
    @Override
    public EntityModel<User> toModel(User entity) {
        return EntityModel.of(entity,
                linkTo(methodOn(UserController.class).retrieveUser(entity.getId())).withSelfRel(),
                linkTo(methodOn(UserController.class).assessRisk(entity.getId())).withRel("assessRisk"));
    }
}
