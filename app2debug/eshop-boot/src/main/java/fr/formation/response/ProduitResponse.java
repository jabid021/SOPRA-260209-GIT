package fr.formation.response;

import java.math.BigDecimal;

import fr.formation.model.Produit;

public class ProduitResponse {
    private int id;
    private String nom;
    private BigDecimal prix;
    private int fournisseurId;
    private String fournisseurNom;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public BigDecimal getPrix() {
        return prix;
    }

    public void setPrix(BigDecimal prix) {
        this.prix = prix;
    }

    public int getFournisseurId() {
        return fournisseurId;
    }

    public void setFournisseurId(int fournisseurId) {
        this.fournisseurId = fournisseurId;
    }

    public String getFournisseurNom() {
        return fournisseurNom;
    }

    public void setFournisseurNom(String fournisseurNom) {
        this.fournisseurNom = fournisseurNom;
    }

    public static ProduitResponse convert(Produit produit) {
        ProduitResponse response = new ProduitResponse();

        response.id = produit.getId();
        response.nom = produit.getNom();
        response.prix = produit.getPrix();

        if (produit.getFournisseur() != null) {
            response.fournisseurId = produit.getFournisseur().getId();
            response.fournisseurNom = produit.getFournisseur().getNom();
        }

        return response;
    }
}
