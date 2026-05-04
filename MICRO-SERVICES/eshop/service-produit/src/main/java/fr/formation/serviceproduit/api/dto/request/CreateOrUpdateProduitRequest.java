package fr.formation.serviceproduit.api.dto.request;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CreateOrUpdateProduitRequest(@NotBlank String libelle, @NotNull @Positive BigDecimal prix) {

}
