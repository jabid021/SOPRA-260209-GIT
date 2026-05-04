package fr.formation.servicecommande.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record CreateCommandeDetailRequest(@NotBlank String produitId, @Positive int quantite) {

}
