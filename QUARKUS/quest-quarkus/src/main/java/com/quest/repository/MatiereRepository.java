package com.quest.repository;

import java.util.Optional;

import com.quest.model.Matiere;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MatiereRepository implements PanacheRepositoryBase<Matiere, Integer> {
    public Optional<Matiere> findByLibelle(String libelle) {
        return this.find("libelle", libelle).firstResultOptional();
    }
}
