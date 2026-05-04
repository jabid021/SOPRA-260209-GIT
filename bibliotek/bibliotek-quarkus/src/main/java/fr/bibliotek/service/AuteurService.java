package fr.bibliotek.service;

import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.bibliotek.api.request.CreateOrUpdateAuteurRequest;
import fr.bibliotek.exception.AuteurNotFoundException;
import fr.bibliotek.model.Auteur;
import fr.bibliotek.repo.AuteurRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class AuteurService {
    private static final Logger log = LoggerFactory.getLogger(AuteurService.class);
    private final AuteurRepository repository;

    public AuteurService(AuteurRepository repository) {
        this.repository = repository;
    }

    public Stream<Auteur> findAll() {
        log.debug("Listing authors from repository ...");

        return this.repository.findAll().stream();
    }

    public Auteur findById(String id) {
        log.debug("Finding author {} from repository ...", id);

        return this.repository.findByIdOptional(id).orElseThrow(AuteurNotFoundException::new);
    }

    @Transactional
    public Auteur save(CreateOrUpdateAuteurRequest request) {
        log.debug("Adding new author ...");

        return this.save(new Auteur(), request);
    }

    @Transactional
    public Auteur save(String id, CreateOrUpdateAuteurRequest request) {
        log.debug("Editing author {} ...", id);

        Auteur auteur = this.findById(id);

        return this.save(auteur, request);
    }

    @Transactional
    public void deleteById(String id) {
        log.debug("Deleting author {} ...", id);

        this.repository.deleteById(id);

        log.debug("Author {} deleted!", id);
    }

    private Auteur save(Auteur auteur, CreateOrUpdateAuteurRequest request) {
        auteur.setNom(request.getNom());
        auteur.setPrenom(request.getPrenom());
        auteur.setNationalite(request.getNationalite());

        this.repository.persist(auteur);

        log.debug("Author {} persisted!", auteur.getId());

        return auteur;
    }
}
