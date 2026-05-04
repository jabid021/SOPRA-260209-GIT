package fr.bibliotek.api;

import java.util.List;

import org.jboss.resteasy.reactive.RestResponse.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.bibliotek.api.request.CreateOrUpdateLivreRequest;
import fr.bibliotek.api.response.EntityCreatedResponse;
import fr.bibliotek.api.response.EntityUpdatedResponse;
import fr.bibliotek.api.response.LivreResponse;
import fr.bibliotek.service.LivreService;
import jakarta.validation.Valid;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;

@Path("/livre")
public class LivreResource {
    private static final Logger log = LoggerFactory.getLogger(LivreResource.class);
    private final LivreService service;

    public LivreResource(LivreService service) {
        this.service = service;
    }

    @GET
    public List<LivreResponse> findAll() {
        log.debug("Listing books ...");

        return this.service.findAll().map(LivreResponse::convert).toList();
    }

    @Path("/{id}")
    @GET
    public LivreResponse findById(@PathParam("id") String id) {
        log.debug("Finding book {} ...", id);

        return LivreResponse.convert(this.service.findById(id));
    }

    @POST
    public Response create(@Valid CreateOrUpdateLivreRequest request) {
        log.debug("Creating new book ...");

        return Response.status(Status.CREATED)
            .entity(new EntityCreatedResponse(this.service.save(request).getId()))
            .build()
        ;
    }

    @Path("/{id}")
    @PUT
    public EntityUpdatedResponse update(@PathParam("id") String id, @Valid CreateOrUpdateLivreRequest request) {
        log.debug("Updating book {} ...", id);

        this.service.save(id, request);

        return new EntityUpdatedResponse(id, true);
    }

    @Path("/{id}")
    @DELETE
    public void deleteById(@PathParam("id") String id) {
        log.debug("Deleting book {} ...", id);

        this.service.deleteById(id);
    }
}
