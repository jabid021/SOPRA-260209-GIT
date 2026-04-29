package fr.formation.model;

import java.math.BigDecimal;

public class Produit {
    private Integer id;
    private String libelle;
    private BigDecimal prix;

    public Produit() { }

    public Produit(Integer id, String libelle, BigDecimal prix) {
        this.id = id;
        this.libelle = libelle;
        this.prix = prix;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public BigDecimal getPrix() {
        return prix;
    }

    public void setPrix(BigDecimal prix) {
        this.prix = prix;
    }
}
