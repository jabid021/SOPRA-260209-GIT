package fr.formation.servicecommande.repo;

import java.util.List;

import fr.formation.servicecommande.model.Commande;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CommandeRepository implements PanacheRepositoryBase<Commande, String> {
    public PanacheQuery<Commande> findAllByClientId(String clientId) {
        return this.find("clientId", clientId);
    }

    public List<String> findAllIdsByClientId(String clientId) {
        return this.find("select c.id from Commande c where c.clientId = ?1", clientId)
            .project(String.class)
            .list()
        ;
    }
}
