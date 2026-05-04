package fr.formation.serviceclient.repo;

import fr.formation.serviceclient.model.Client;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ClientRepository implements PanacheRepositoryBase<Client, String> {

}
