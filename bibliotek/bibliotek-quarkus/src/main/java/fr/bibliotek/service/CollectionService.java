package fr.bibliotek.service;

import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.bibliotek.api.request.CreateOrUpdateCollectionRequest;
import fr.bibliotek.exception.CollectionNotFoundException;
import fr.bibliotek.model.Collection;
import fr.bibliotek.repo.CollectionRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class CollectionService {
    private static final Logger log = LoggerFactory.getLogger(CollectionService.class);
    private final CollectionRepository repository;

    public CollectionService(CollectionRepository repository) {
        this.repository = repository;
    }

    public Stream<Collection> findAll() {
        log.debug("Listing collections from repository ...");

        return this.repository.findAll().stream();
    }

    public Collection findById(String id) {
        log.debug("Finding collection {} from repository ...", id);

        return this.repository.findByIdOptional(id).orElseThrow(CollectionNotFoundException::new);
    }

    @Transactional
    public Collection save(CreateOrUpdateCollectionRequest request) {
        log.debug("Adding new collection ...");

        return this.save(new Collection(), request);
    }

    @Transactional
    public Collection save(String id, CreateOrUpdateCollectionRequest request) {
        log.debug("Editing collection {} ...", id);

        Collection collection = this.findById(id);

        return this.save(collection, request);
    }

    @Transactional
    public void deleteById(String id) {
        log.debug("Deleting collection {} ...", id);

        this.repository.deleteById(id);

        log.debug("Collection {} deleted!", id);
    }

    private Collection save(Collection collection, CreateOrUpdateCollectionRequest request) {
        collection.setNom(request.getNom());

        this.repository.persist(collection);

        log.debug("Collection {} persisted!", collection.getId());

        return collection;
    }
}
