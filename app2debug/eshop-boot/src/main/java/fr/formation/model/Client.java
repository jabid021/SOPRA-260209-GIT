package fr.formation.model;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "client")
@PrimaryKeyJoinColumn(name = "cli_id")
public class Client extends Personne {
    @Column(name = "cli_prenom")
    private String prenom;

    @Column(name = "cli_date_naissance")
    private LocalDate dateNaissance;

    @OneToMany(mappedBy = "client")
    private List<Commande> commandes;

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public LocalDate getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(LocalDate dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public List<Commande> getCommandes() {
        return commandes;
    }

    public void setCommandes(List<Commande> commandes) {
        this.commandes = commandes;
    }
}
