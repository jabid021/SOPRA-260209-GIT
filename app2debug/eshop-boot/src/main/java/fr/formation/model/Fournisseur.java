package fr.formation.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "fournisseur")
@PrimaryKeyJoinColumn(name = "fou_id")
public class Fournisseur extends Personne {
    @OneToMany(mappedBy = "fournisseur")
    private List<Produit> produits;

    public List<Produit> getProduits() {
        return produits;
    }

    public void setProduits(List<Produit> produits) {
        this.produits = produits;
    }
}
