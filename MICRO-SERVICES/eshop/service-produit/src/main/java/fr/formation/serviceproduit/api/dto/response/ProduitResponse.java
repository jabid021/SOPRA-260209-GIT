package fr.formation.serviceproduit.api.dto.response;

import java.math.BigDecimal;

import fr.formation.serviceproduit.model.Produit;

public record ProduitResponse(String id, String libelle, BigDecimal prix) {
    public static ProduitResponse convert(Produit produit) {
        return new ProduitResponse(produit.getId(), produit.getLibelle(), produit.getPrix());
    }
}
