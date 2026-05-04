package fr.bibliotek.repo;

import fr.bibliotek.model.Editeur;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class EditeurRepository implements PanacheRepositoryBase<Editeur, String> {

}
