package me.portfolioof.application.controller;

import me.portfolioof.application.assembler.RiskAssessmentEntityAssembler;
import me.portfolioof.application.assembler.UserEntityAssembler;
import me.portfolioof.application.entity.RiskAssessment;
import me.portfolioof.application.entity.User;
import me.portfolioof.application.service.UserService;
import me.portfolioof.application.service.UserServiceImpl;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final UserEntityAssembler assembler;
    private final RiskAssessmentEntityAssembler riskAssessmentEntityAssembler;

    public UserController(UserServiceImpl userService, UserEntityAssembler assembler, RiskAssessmentEntityAssembler riskAssessmentEntityAssembler) {
        this.userService = userService;
        this.assembler = assembler;
        this.riskAssessmentEntityAssembler = riskAssessmentEntityAssembler;
    }

    @GetMapping("/{uid}")
    public EntityModel<User> retrieveUser(@PathVariable("uid") Long uid) {
        return assembler.toModel(userService.retrieveUser(uid));
    }

    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User user) {
        EntityModel<User> userEntityModel = assembler.toModel(userService.updateUser(user));
        return ResponseEntity.created(userEntityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(userEntityModel);
    }

    @GetMapping("/{uid}/assess")
    public EntityModel<RiskAssessment> assessRisk(@PathVariable("uid") Long uid) {
        return riskAssessmentEntityAssembler.toModel(userService.assessRisk(uid));
    }

}
