package fr.formation.serviceproduit.repo;

import fr.formation.serviceproduit.model.Produit;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ProduitRepository implements PanacheRepositoryBase<Produit, String> {

}
