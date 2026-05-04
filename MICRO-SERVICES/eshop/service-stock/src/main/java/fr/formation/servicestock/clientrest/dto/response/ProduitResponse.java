package fr.formation.servicestock.clientrest.dto.response;

import java.math.BigDecimal;

public record ProduitResponse(String id, String libelle, BigDecimal prix) {

}
