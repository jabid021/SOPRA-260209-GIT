package fr.bibliotek.service;

import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.bibliotek.api.request.CreateOrUpdateLivreRequest;
import fr.bibliotek.exception.LivreNotFoundException;
import fr.bibliotek.model.Livre;
import fr.bibliotek.repo.AuteurRepository;
import fr.bibliotek.repo.CollectionRepository;
import fr.bibliotek.repo.EditeurRepository;
import fr.bibliotek.repo.LivreRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class LivreService {
    private static final Logger log = LoggerFactory.getLogger(AuteurService.class);

    private final LivreRepository repository;
    private final AuteurRepository auteurRepository;
    private final EditeurRepository editeurRepository;
    private final CollectionRepository collectionRepository;

    public LivreService(LivreRepository repository, AuteurRepository auteurRepository, EditeurRepository editeurRepository, CollectionRepository collectionRepository) {
        this.repository = repository;
        this.auteurRepository = auteurRepository;
        this.editeurRepository = editeurRepository;
        this.collectionRepository = collectionRepository;
    }

    public Stream<Livre> findAll() {
        log.debug("Listing books from repository ...");

        return this.repository.findAll().stream();
    }

    public Livre findById(String id) {
        log.debug("Finding book {} from repository ...", id);

        return this.repository.findByIdOptional(id).orElseThrow(LivreNotFoundException::new);
    }

    @Transactional
    public Livre save(CreateOrUpdateLivreRequest request) {
        log.debug("Adding new book ...");

        return this.save(new Livre(), request);
    }

    @Transactional
    public Livre save(String id, CreateOrUpdateLivreRequest request) {
        log.debug("Editing book {} ...", id);

        Livre livre = this.findById(id);

        return this.save(livre, request);
    }

    @Transactional
    public void deleteById(String id) {
        log.debug("Deleting book {} ...", id);

        this.repository.deleteById(id);

        log.debug("Book {} deleted!", id);
    }

    private Livre save(Livre livre, CreateOrUpdateLivreRequest request) {
        livre.setNom(request.getNom());
        livre.setResume(request.getResume());
        livre.setPublication(request.getPublication());

        livre.setAuteur(this.auteurRepository.findById(request.getAuteurId()));
        livre.setEditeur(this.editeurRepository.findById(request.getEditeurId()));

        if (request.getCollectionId() != null && !request.getCollectionId().isBlank()) {
            livre.setCollection(this.collectionRepository.findById(request.getCollectionId()));
        }

        else {
            livre.setCollection(null);
        }

        this.repository.persist(livre);

        log.debug("Book {} persisted!", livre.getId());

        return livre;
    }
}
