package fr.formation.servicecommande.api.dto.response;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import fr.formation.servicecommande.model.Commande;

public class CommandeResponse {
    private String id;
    private BigDecimal total;
    private String clientNom;
    private String clientPrenom;
    private List<CommandeDetailResponse> produits = new ArrayList<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public String getClientNom() {
        return clientNom;
    }

    public void setClientNom(String clientNom) {
        this.clientNom = clientNom;
    }

    public String getClientPrenom() {
        return clientPrenom;
    }

    public void setClientPrenom(String clientPrenom) {
        this.clientPrenom = clientPrenom;
    }

    public List<CommandeDetailResponse> getProduits() {
        return produits;
    }

    public void setProduits(List<CommandeDetailResponse> produits) {
        this.produits = produits;
    }

    public CommandeResponse(String id, BigDecimal total) {
        this.id = id;
        this.total = total;
    }

    public static CommandeResponse convert(Commande commande) {
        return new CommandeResponse(commande.getId(), commande.getTotal());
    }
}
