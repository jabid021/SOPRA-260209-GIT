package fr.bibliotek.api;

import org.jboss.resteasy.reactive.RestResponse.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.bibliotek.api.request.SubscriptionRequest;
import fr.bibliotek.api.response.EntityCreatedResponse;
import fr.bibliotek.model.Utilisateur;
import fr.bibliotek.repo.UtilisateurRepository;
import io.quarkus.elytron.security.common.BcryptUtil;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@Path("/utilisateur")
public class UtilisateurResource {
    private static final Logger log = LoggerFactory.getLogger(UtilisateurResource.class);
    private final UtilisateurRepository repository;

    public UtilisateurResource(UtilisateurRepository repository) {
        this.repository = repository;
    }

    @Path("/inscription")
    @POST
    @Transactional
    public Response subscribe(@Valid SubscriptionRequest request) {
        log.debug("Subscribing new user ...");

        Utilisateur utilisateur = new Utilisateur();

        utilisateur.setUsername(request.getUsername());
        utilisateur.setPassword(BcryptUtil.bcryptHash(request.getPassword()));

        this.repository.persist(utilisateur);

        log.debug("User {} subscribed!", utilisateur.getId());

        return Response.status(Status.CREATED)
            .entity(new EntityCreatedResponse(utilisateur.getId()))
            .build()
        ;
    }
}
