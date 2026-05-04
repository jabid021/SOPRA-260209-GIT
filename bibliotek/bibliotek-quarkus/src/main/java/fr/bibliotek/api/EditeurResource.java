package fr.bibliotek.api;

import java.util.List;

import org.jboss.resteasy.reactive.RestResponse.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.bibliotek.api.request.CreateOrUpdateEditeurRequest;
import fr.bibliotek.api.response.EditeurResponse;
import fr.bibliotek.api.response.EntityCreatedResponse;
import fr.bibliotek.api.response.EntityUpdatedResponse;
import fr.bibliotek.service.EditeurService;
import jakarta.validation.Valid;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;

@Path("/editeur")
public class EditeurResource {
    private static final Logger log = LoggerFactory.getLogger(EditeurResource.class);
    private final EditeurService service;

    public EditeurResource(EditeurService service) {
        this.service = service;
    }

    @GET
    public List<EditeurResponse> findAll() {
        log.debug("Listing editors ...");

        return this.service.findAll().map(EditeurResponse::convert).toList();
    }

    @Path("/{id}")
    @GET
    public EditeurResponse findById(@PathParam("id") String id) {
        log.debug("Finding editor {} ...", id);

        return EditeurResponse.convert(this.service.findById(id));
    }

    @POST
    public Response create(@Valid CreateOrUpdateEditeurRequest request) {
        log.debug("Creating new editor ...");

        return Response.status(Status.CREATED)
            .entity(new EntityCreatedResponse(this.service.save(request).getId()))
            .build()
        ;
    }

    @Path("/{id}")
    @PUT
    public EntityUpdatedResponse update(@PathParam("id") String id, @Valid CreateOrUpdateEditeurRequest request) {
        log.debug("Updating editor {} ...", id);

        this.service.save(id, request);

        return new EntityUpdatedResponse(id, true);
    }

    @Path("/{id}")
    @DELETE
    public void deleteById(@PathParam("id") String id) {
        log.debug("Deleting editor {} ...", id);

        this.service.deleteById(id);
    }
}
