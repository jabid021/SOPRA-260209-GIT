package fr.formation.servicecommande.clientrest;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import fr.formation.servicecommande.clientrest.dto.response.ClientResponse;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;

@Path("/")
@RegisterRestClient(configKey = "client-service")
public interface ClientClientRest {
    @GET
    @Path("/{id}")
    public ClientResponse findById(@PathParam("id") String id);
}
