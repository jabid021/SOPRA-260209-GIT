package fr.formation.servicecommande.api.dto.request;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateCommandeRequest(@NotBlank String clientId, @NotNull @Valid List<CreateCommandeDetailRequest> produits) {

}
