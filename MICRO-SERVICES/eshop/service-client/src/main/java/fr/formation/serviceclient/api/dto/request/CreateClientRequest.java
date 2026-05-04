package fr.formation.serviceclient.api.dto.request;

import jakarta.validation.constraints.NotBlank;

public record CreateClientRequest(@NotBlank String nom, @NotBlank String prenom) {

}
