package fr.formation.response;

import java.math.BigDecimal;

import fr.formation.model.Produit;

public class ProduitResponse {
    private Integer id;
    private String libelle;
    private BigDecimal prix;

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

    public static ProduitResponse convert(Produit produit) {
        ProduitResponse resp = new ProduitResponse();

        resp.setId(produit.getId());
        resp.setLibelle(produit.getLibelle());
        resp.setPrix(produit.getPrix());

        return resp;
    }
}
