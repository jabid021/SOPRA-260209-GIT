package com.quest.api;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.quest.dao.IDAOMatiere;
import com.quest.dto.request.CreateOrUpdateMatiereRequest;
import com.quest.dto.response.EntityCreatedOrUpdatedResponse;
import com.quest.dto.response.MatiereResponse;
import com.quest.exception.MatiereNotFoundException;
import com.quest.model.Matiere;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/matiere")
public class MatiereApiController {
    private static final Logger log = LoggerFactory.getLogger(MatiereApiController.class);

    private final IDAOMatiere dao;

    public MatiereApiController(IDAOMatiere dao) {
        this.dao = dao;
    }

    @GetMapping
    public List<MatiereResponse> findAll() {
        log.debug("Liste des matières ...");

        return this.dao.findAll().stream().map(MatiereResponse::convert).toList();
    }

    @GetMapping("/{id}")
    public MatiereResponse findById(@PathVariable Integer id) {
        log.debug("Matière {} ...", id);

        return this.dao.findById(id).map(MatiereResponse::convert).orElseThrow(MatiereNotFoundException::new);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EntityCreatedOrUpdatedResponse create(@Valid @RequestBody CreateOrUpdateMatiereRequest request) {
        log.debug("Création d'une nouvelle matière ...");

        Matiere matiere = new Matiere();

        matiere.setLibelle(request.getLibelle());

        this.dao.save(matiere);

        log.debug("Matière {} créée !", matiere.getId());

        return new EntityCreatedOrUpdatedResponse(matiere.getId());
    }

    @PutMapping("/{id}")
    public EntityCreatedOrUpdatedResponse update(@PathVariable Integer id, @Valid @RequestBody CreateOrUpdateMatiereRequest request) {
        log.debug("Modification de la matière {} ...", id);

        Matiere matiere = this.dao.findById(id).orElseThrow(EntityNotFoundException::new);

        matiere.setLibelle(request.getLibelle());

        this.dao.save(matiere);

        log.debug("Matière {} modifiée !", id);

        return new EntityCreatedOrUpdatedResponse(matiere.getId());
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Integer id) {
        log.debug("Suppression de la matière {} ...", id);

        this.dao.deleteById(id);

        log.debug("Matière {} supprimée !", id);
    }
}
