package com.quest.dto.request;

import jakarta.validation.constraints.NotBlank;

public record CreateOrUpdateMatiereRequest(@NotBlank String libelle) {

}
