package fr.formation.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fr.formation.model.Utilisateur;

public interface IDAOUtilisateur extends JpaRepository<Utilisateur, Integer> {
    public Utilisateur findByUsername(String username);

    @Query("select u from Utilisateur u where u.username = ?1")
    public Optional<Utilisateur> findByUsernameOptional(String username);
}
