package fr.formation.rest.dto.response.request;

import jakarta.validation.constraints.NotBlank;

public class CreateProduitRequest {
    @NotBlank
    private String nom;

    @NotBlank
    private String code;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
