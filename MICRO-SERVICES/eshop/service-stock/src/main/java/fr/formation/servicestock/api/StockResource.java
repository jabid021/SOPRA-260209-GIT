package fr.formation.servicestock.api;

import java.util.Optional;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import fr.formation.servicestock.api.dto.request.MoveStockRequest;
import fr.formation.servicestock.clientrest.ProduitClientRest;
import fr.formation.servicestock.model.Stock;
import fr.formation.servicestock.repo.StockRepository;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;

@Path("/api/stock/{produitId}")
public class StockResource {
    private final StockRepository repository;

    @RestClient
    @Inject
    private ProduitClientRest produitClientRest;

    public StockResource(StockRepository repository) {
        this.repository = repository;
    }

    @GET
    public int findQuantity(@PathParam("produitId") String produitId) {
        this.produitClientRest.findById(produitId);

        return this.repository.findByIdOptional(produitId).orElseThrow(NotFoundException::new).getQuantite();
    }

    @POST
    @Transactional
    public String adjustQuantity(@PathParam("produitId") String produitId, @Valid MoveStockRequest request) {
        Optional<Stock> optStock = this.repository.findByIdOptional(produitId);
        Stock stock = null;

        if (optStock.isEmpty()) {
            stock = new Stock();
            stock.setId(produitId);
        }

        else {
            stock = optStock.get();
        }

        this.produitClientRest.findById(produitId);
        stock.setQuantite(stock.getQuantite() + request.delta());

        this.repository.persist(stock);

        return produitId;
    }
}
