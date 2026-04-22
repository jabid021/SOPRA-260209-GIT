package com.quest.dto.request;

import com.quest.model.Genre;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class SubscriptionRequest {
    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @NotBlank
    private String nom;

    @NotBlank
    private String prenom;

    @NotNull
    private Genre civilite;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Genre getCivilite() {
        return civilite;
    }

    public void setCivilite(Genre civilite) {
        this.civilite = civilite;
    }
}
