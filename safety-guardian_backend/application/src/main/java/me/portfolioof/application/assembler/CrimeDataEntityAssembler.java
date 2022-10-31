package me.portfolioof.application.assembler;

import me.portfolioof.application.controller.CrimeDataController;
import me.portfolioof.application.entity.CrimeData;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class CrimeDataEntityAssembler implements RepresentationModelAssembler<CrimeData, EntityModel<CrimeData>> {
    @Override
    public EntityModel<CrimeData> toModel(CrimeData entity) {
        return EntityModel.of(entity,
                linkTo(methodOn(CrimeDataController.class).retrieveCrimeData(entity.getId())).withSelfRel(),
                linkTo(methodOn(CrimeDataController.class).retrieveCrimeDataPage(Pageable.unpaged())).withRel("crimeDataPage"));
    }

}
