package fr.bibliotek.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.bibliotek.api.request.AuthRequest;
import fr.bibliotek.api.response.AuthResponse;
import io.quarkus.security.credential.PasswordCredential;
import io.quarkus.security.identity.SecurityIdentity;
import io.quarkus.security.identity.request.UsernamePasswordAuthenticationRequest;
import io.quarkus.security.jpa.runtime.JpaIdentityProvider;
import io.smallrye.jwt.build.Jwt;
import jakarta.persistence.EntityManager;
import jakarta.validation.Valid;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;

@Path("/auth")
public class AuthResource {
    private static final Logger log = LoggerFactory.getLogger(AuthResource.class);

    private final JpaIdentityProvider jpaIdentityProvider;
    private final EntityManager entityManager;

    public AuthResource(JpaIdentityProvider jpaIdentityProvider, EntityManager entityManager) {
        this.jpaIdentityProvider = jpaIdentityProvider;
        this.entityManager = entityManager;
    }

    @POST
    public AuthResponse auth(@Valid AuthRequest request) {
        log.debug("Responding to authentication ...");

        UsernamePasswordAuthenticationRequest authenticationRequest = new UsernamePasswordAuthenticationRequest(
            request.getUsername(),
            new PasswordCredential(request.getPassword().toCharArray())
        );

        SecurityIdentity identity = this.jpaIdentityProvider.authenticate(this.entityManager, authenticationRequest);

        String jwt = Jwt.issuer("bibliotek-quarkus-issuer")
            .upn(request.getUsername()) // User Principal Name
            .groups(identity.getRoles()) // Le ou les rôles
            .sign() // On signe le jeton avec la clé privée
        ;

        return new AuthResponse(true, jwt);
    }
}
