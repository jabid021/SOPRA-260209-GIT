package fr.formation.servicecommande.model;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Commande {
    @Id
    @UuidGenerator
    private String id;

    @Column(nullable = false)
    private BigDecimal total;

    @Column(nullable = false)
    private String clientId;

    @OneToMany(mappedBy = "commande", cascade = CascadeType.ALL)
    private List<CommandeDetail> details;

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

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public List<CommandeDetail> getDetails() {
        return details;
    }

    public void setDetails(List<CommandeDetail> details) {
        this.details = details;
    }
}
