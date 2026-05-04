package fr.formation.serviceclient.clientrest;

import java.util.List;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;

@Path("/")
@RegisterRestClient(configKey = "commande-service")
public interface CommandeClientRest {
    @GET
    @Path("/by-client-id/{clientId}")
    public List<String> findAllByClientId(@PathParam("clientId") String clientId);
}
