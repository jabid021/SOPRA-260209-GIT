package fr.bibliotek.repo;

import fr.bibliotek.model.Collection;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CollectionRepository implements PanacheRepositoryBase<Collection, String> {

}
