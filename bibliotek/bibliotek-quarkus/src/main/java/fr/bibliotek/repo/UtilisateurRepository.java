package fr.bibliotek.repo;

import java.util.Optional;

import fr.bibliotek.model.Utilisateur;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UtilisateurRepository implements PanacheRepositoryBase<Utilisateur, String> {
    public Optional<Utilisateur> findByUsername(String username) {
        return this.find("username = ?1", username).firstResultOptional();
    }
}
