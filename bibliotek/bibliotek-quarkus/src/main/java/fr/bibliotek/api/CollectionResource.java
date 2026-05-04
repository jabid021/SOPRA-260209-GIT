package fr.bibliotek.api;

import java.util.List;

import org.jboss.resteasy.reactive.RestResponse.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.bibliotek.api.request.CreateOrUpdateCollectionRequest;
import fr.bibliotek.api.response.CollectionResponse;
import fr.bibliotek.api.response.EntityCreatedResponse;
import fr.bibliotek.api.response.EntityUpdatedResponse;
import fr.bibliotek.service.CollectionService;
import jakarta.validation.Valid;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;

@Path("/collection")
public class CollectionResource {
    private static final Logger log = LoggerFactory.getLogger(CollectionResource.class);
    private final CollectionService service;

    public CollectionResource(CollectionService service) {
        this.service = service;
    }

    @GET
    public List<CollectionResponse> findAll() {
        log.debug("Listing collections ...");

        return this.service.findAll().map(CollectionResponse::convert).toList();
    }

    @Path("/{id}")
    @GET
    public CollectionResponse findById(@PathParam("id") String id) {
        log.debug("Finding collection {} ...", id);

        return CollectionResponse.convert(this.service.findById(id));
    }

    @POST
    public Response create(@Valid CreateOrUpdateCollectionRequest request) {
        log.debug("Creating new collection ...");

        return Response.status(Status.CREATED)
            .entity(new EntityCreatedResponse(this.service.save(request).getId()))
            .build()
        ;
    }

    @Path("/{id}")
    @PUT
    public EntityUpdatedResponse update(@PathParam("id") String id, @Valid CreateOrUpdateCollectionRequest request) {
        log.debug("Updating collection {} ...", id);

        this.service.save(id, request);

        return new EntityUpdatedResponse(id, true);
    }

    @Path("/{id}")
    @DELETE
    public void deleteById(@PathParam("id") String id) {
        log.debug("Deleting collection {} ...", id);

        this.service.deleteById(id);
    }
}
