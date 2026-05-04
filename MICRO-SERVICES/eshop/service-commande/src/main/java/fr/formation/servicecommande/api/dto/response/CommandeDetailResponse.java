package fr.formation.servicecommande.api.dto.response;

import java.math.BigDecimal;

import fr.formation.servicecommande.model.CommandeDetail;

public class CommandeDetailResponse {
    private String produitId;
    private String produitLibelle;
    private BigDecimal produitPrix;
    private int quantite;

    public String getProduitId() {
        return produitId;
    }

    public void setProduitId(String produitId) {
        this.produitId = produitId;
    }

    public String getProduitLibelle() {
        return produitLibelle;
    }

    public void setProduitLibelle(String produitLibelle) {
        this.produitLibelle = produitLibelle;
    }

    public BigDecimal getProduitPrix() {
        return produitPrix;
    }

    public void setProduitPrix(BigDecimal produitPrix) {
        this.produitPrix = produitPrix;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public static CommandeDetailResponse convert(CommandeDetail detail) {
        CommandeDetailResponse resp = new CommandeDetailResponse();

        resp.setProduitId(detail.getProduitId());
        resp.setProduitLibelle(detail.getProduitLibelle());
        resp.setProduitPrix(detail.getProduitPrix());
        resp.setQuantite(detail.getQuantite());

        return resp;
    }
}
