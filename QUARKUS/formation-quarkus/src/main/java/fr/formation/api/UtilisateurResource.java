package fr.formation.api;

import fr.formation.model.Utilisateur;
import fr.formation.repository.UtilisateurRepository;
import fr.formation.request.SubscribeRequest;
import io.quarkus.elytron.security.common.BcryptUtil;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@Path("/api")
public class UtilisateurResource {
    private final UtilisateurRepository repository;

    public UtilisateurResource(UtilisateurRepository repository) {
        this.repository = repository;
    }

    @Transactional
    @POST
    @Path("/inscription")
    public Response subscribe(SubscribeRequest request) {
        Utilisateur utilisateur = new Utilisateur();
        String hashedPassword = BcryptUtil.bcryptHash(request.password());

        utilisateur.setUsername(request.username());
        utilisateur.setPassword(hashedPassword);

        this.repository.persist(utilisateur);

        return Response.status(Response.Status.CREATED)
            .entity(utilisateur.getId())
            .build()
        ;
    }
}
