package me.portfolioof.application.controller;

import me.portfolioof.application.DAO.CrimeDataDAO;
import me.portfolioof.application.assembler.CrimeDataEntityAssembler;
import me.portfolioof.application.entity.CrimeData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class CrimeDataController {

    private final CrimeDataEntityAssembler assembler;
    private final CrimeDataDAO crimeDataDAO;

    public CrimeDataController(CrimeDataEntityAssembler assembler, CrimeDataDAO crimeDataDAO) {
        this.assembler = assembler;
        this.crimeDataDAO = crimeDataDAO;
    }

    @GetMapping("/crime_data")
    public PagedModel<EntityModel<CrimeData>> retrieveCrimeDataPage(@PageableDefault Pageable pageable) {
        Page<CrimeData> crimeDataPage = crimeDataDAO.findAll(pageable);
        Collection<EntityModel<CrimeData>> crimeDataModels = crimeDataPage.getContent().stream()
                .map(assembler::toModel).collect(Collectors.toList());

        return PagedModel.of(crimeDataModels, new PagedModel.PageMetadata(
                        crimeDataPage.getSize(),
                        crimeDataPage.getNumber(),
                        crimeDataPage.getTotalElements(),
                        crimeDataPage.getTotalPages()),
                linkTo(methodOn(CrimeDataController.class).retrieveCrimeDataPage(pageable)).withSelfRel());
    }

    @GetMapping("/crime_data/{cid}")
    public EntityModel<CrimeData> retrieveCrimeData(@PathVariable(name = "cid") Long cid) {
        return assembler.toModel(crimeDataDAO.findById(cid).get());
    }
}
