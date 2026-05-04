package fr.formation.servicestock.repo;

import fr.formation.servicestock.model.Stock;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class StockRepository implements PanacheRepositoryBase<Stock, String> {

}
