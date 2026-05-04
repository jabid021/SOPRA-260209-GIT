package fr.formation.servicecommande.clientrest.dto.response;

import java.math.BigDecimal;

public record ProduitResponse(String id, String libelle, BigDecimal prix) {

}
