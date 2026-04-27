package fr.formation.model;

import java.time.LocalDateTime;
import java.util.List;

import fr.formation.enumerator.EtatCommande;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "commande")
public class Commande {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cmd_id")
    private int id;

    @Column(name = "cmd_date", nullable = false)
    private LocalDateTime date;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "cmd_etat", nullable = false)
    private EtatCommande etat;

    @ManyToOne
    @JoinColumn(name = "cmd_client_id", nullable = false)
    private Client client;

    @OneToMany(mappedBy = "commande", cascade = CascadeType.PERSIST)
    private List<CommandeDetail> details;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public EtatCommande getEtat() {
        return etat;
    }

    public void setEtat(EtatCommande etat) {
        this.etat = etat;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<CommandeDetail> getDetails() {
        return details;
    }

    public void setDetails(List<CommandeDetail> details) {
        this.details = details;
    }
}
