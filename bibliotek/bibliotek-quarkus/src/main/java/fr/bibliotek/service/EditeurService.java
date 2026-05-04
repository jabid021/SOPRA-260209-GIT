package fr.bibliotek.service;

import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.bibliotek.api.request.CreateOrUpdateEditeurRequest;
import fr.bibliotek.exception.EditeurNotFoundException;
import fr.bibliotek.model.Editeur;
import fr.bibliotek.repo.EditeurRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class EditeurService {
    private static final Logger log = LoggerFactory.getLogger(AuteurService.class);
    private final EditeurRepository repository;

    public EditeurService(EditeurRepository repository) {
        this.repository = repository;
    }

    public Stream<Editeur> findAll() {
        log.debug("Listing editors from repository ...");

        return this.repository.findAll().stream();
    }

    public Editeur findById(String id) {
        log.debug("Finding editor {} from repository ...", id);

        return this.repository.findByIdOptional(id).orElseThrow(EditeurNotFoundException::new);
    }

    @Transactional
    public Editeur save(CreateOrUpdateEditeurRequest request) {
        log.debug("Adding new editor ...");

        return this.save(new Editeur(), request);
    }

    @Transactional
    public Editeur save(String id, CreateOrUpdateEditeurRequest request) {
        log.debug("Editing editor {} ...", id);

        Editeur editeur = this.findById(id);

        return this.save(editeur, request);
    }

    @Transactional
    public void deleteById(String id) {
        log.debug("Deleting editor {} ...", id);

        this.repository.deleteById(id);

        log.debug("Editor {} deleted!", id);
    }

    private Editeur save(Editeur editeur, CreateOrUpdateEditeurRequest request) {
        editeur.setNom(request.getNom());
        editeur.setPays(request.getPays());

        this.repository.persist(editeur);

        log.debug("Editor {} persisted!", editeur.getId());

        return editeur;
    }
}
