package fr.formation.servicestock.api.dto.request;

import jakarta.validation.constraints.NotNull;

public record MoveStockRequest(@NotNull Integer delta) {

}
