package com.quest.dto.request;

import jakarta.validation.constraints.NotBlank;

public class CreateOrUpdateMatiereRequest {
    @NotBlank
    private String libelle;

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
}
