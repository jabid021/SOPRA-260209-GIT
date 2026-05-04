package fr.formation.serviceclient.api;

import java.util.List;
import java.util.Map;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import fr.formation.serviceclient.api.dto.request.CreateClientRequest;
import fr.formation.serviceclient.api.dto.response.ClientResponse;
import fr.formation.serviceclient.clientrest.CommandeClientRest;
import fr.formation.serviceclient.model.Client;
import fr.formation.serviceclient.repo.ClientRepository;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;

@Path("/api/client")
public class ClientResource {
    private final ClientRepository repository;

    @RestClient
    @Inject
    private CommandeClientRest commandeClientRest;

    public ClientResource(ClientRepository repository) {
        this.repository = repository;
    }

    @GET
    public List<ClientResponse> findAll() {
        return this.repository.findAll().stream().map(ClientResponse::convert).toList();
    }

    @GET
    @Path("/{id}")
    public ClientResponse findById(@PathParam("id") String id) {
        return this.repository.findByIdOptional(id).map(ClientResponse::convert).orElseThrow(NotFoundException::new);
    }

    @POST
    @Transactional
    public Response create(@Valid CreateClientRequest request) {
        Client client = new Client();

        client.setNom(request.nom());
        client.setPrenom(request.prenom());

        this.repository.persist(client);

        return Response.status(Response.Status.CREATED)
            .entity(client.getId())
            .build()
        ;
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deleteById(@PathParam("id") String id) {
        List<String> commandeIds = this.commandeClientRest.findAllByClientId(id);

        if (!commandeIds.isEmpty()) {
            return Response.status(Response.Status.FORBIDDEN)
                .entity(Map.of("error", "customer has orders"))
                .build()
            ;
        }

        this.repository.findByIdOptional(id).orElseThrow(NotFoundException::new);

        return Response.ok(id).build();
    }
}
