package fr.bibliotek.api;

import java.util.List;

import org.jboss.resteasy.reactive.RestResponse.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.bibliotek.api.request.CreateOrUpdateAuteurRequest;
import fr.bibliotek.api.response.AuteurResponse;
import fr.bibliotek.api.response.EntityCreatedResponse;
import fr.bibliotek.api.response.EntityUpdatedResponse;
import fr.bibliotek.service.AuteurService;
import jakarta.validation.Valid;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;

@Path("/auteur")
public class AuteurResource {
    private static final Logger log = LoggerFactory.getLogger(AuteurResource.class);
    private final AuteurService service;

    public AuteurResource(AuteurService service) {
        this.service = service;
    }

    @GET
    public List<AuteurResponse> findAll() {
        log.debug("Listing authors ...");

        return this.service.findAll().map(AuteurResponse::convert).toList();
    }

    @Path("/{id}")
    @GET
    public AuteurResponse findById(@PathParam("id") String id) {
        log.debug("Finding author {} ...", id);

        return AuteurResponse.convert(this.service.findById(id));
    }

    @POST
    public Response create(@Valid CreateOrUpdateAuteurRequest request) {
        log.debug("Creating new author ...");

        return Response.status(Status.CREATED)
            .entity(new EntityCreatedResponse(this.service.save(request).getId()))
            .build()
        ;
    }

    @Path("/{id}")
    @PUT
    public EntityUpdatedResponse update(@PathParam("id") String id, @Valid CreateOrUpdateAuteurRequest request) {
        log.debug("Updating author {} ...", id);

        this.service.save(id, request);

        return new EntityUpdatedResponse(id, true);
    }

    @Path("/{id}")
    @DELETE
    public void deleteById(@PathParam("id") String id) {
        log.debug("Deleting author {} ...", id);

        this.service.deleteById(id);
    }
}
