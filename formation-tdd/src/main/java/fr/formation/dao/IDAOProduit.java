package fr.formation.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.formation.model.Produit;

public interface IDAOProduit extends JpaRepository<Produit, Integer> {
    public List<Produit> findByNom(String nom);
}
