package fr.formation.servicecommande.clientrest;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import fr.formation.servicecommande.clientrest.dto.response.ProduitResponse;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;

@Path("/")
@RegisterRestClient(configKey = "produit-service")
public interface ProduitClientRest {
    @GET
    @Path("/{id}")
    public ProduitResponse findById(@PathParam("id") String id);
}
