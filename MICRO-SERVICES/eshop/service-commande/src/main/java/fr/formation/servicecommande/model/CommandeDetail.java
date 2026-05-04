package fr.formation.servicecommande.model;

import java.math.BigDecimal;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class CommandeDetail {
    @Id
    @UuidGenerator
    private String id;

    @Column(nullable = false)
    private String produitId;

    @Column(nullable = false)
    private String produitLibelle;

    @Column(nullable = false)
    private BigDecimal produitPrix;

    @Column(nullable = false)
    private int quantite;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Commande commande;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public Commande getCommande() {
        return commande;
    }

    public void setCommande(Commande commande) {
        this.commande = commande;
    }
}
