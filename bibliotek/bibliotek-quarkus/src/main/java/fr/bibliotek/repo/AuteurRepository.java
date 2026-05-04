package fr.bibliotek.repo;

import fr.bibliotek.model.Auteur;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AuteurRepository implements PanacheRepositoryBase<Auteur, String> {

}
