package fr.formation.servicecommande.clientrest;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import fr.formation.servicecommande.clientrest.dto.request.MoveStockRequest;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;

@Path("/{produitId}")
@RegisterRestClient(configKey = "stock-service")
public interface StockClientRest {
    @GET
    public int findQuantity(@PathParam("produitId") String produitId);

    @POST
    public String adjustQuantity(@PathParam("produitId") String produitId, MoveStockRequest request);
}
