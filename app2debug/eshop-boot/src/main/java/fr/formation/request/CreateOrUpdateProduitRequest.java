package fr.formation.request;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public class CreateOrUpdateProduitRequest {
    @NotBlank
    private String nom;

    @Positive
    private BigDecimal prix;

    @Positive
    private Integer fournisseurId;

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

    public Integer getFournisseurId() {
        return fournisseurId;
    }

    public void setFournisseurId(Integer fournisseurId) {
        this.fournisseurId = fournisseurId;
    }
}
