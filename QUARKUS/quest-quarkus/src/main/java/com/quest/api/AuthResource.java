package com.quest.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.quest.dto.request.AuthRequest;
import com.quest.dto.response.AuthResponse;

import io.quarkus.security.credential.PasswordCredential;
import io.quarkus.security.identity.SecurityIdentity;
import io.quarkus.security.identity.request.UsernamePasswordAuthenticationRequest;
import io.quarkus.security.jpa.runtime.JpaIdentityProvider;
import io.smallrye.jwt.build.Jwt;
import jakarta.persistence.EntityManager;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;

@Path("/api/auth")
public class AuthResource {
    private static Logger log = LoggerFactory.getLogger(AuthResource.class);
    private final JpaIdentityProvider jpaIdentityProvider;
    private final EntityManager entityManager;

    public AuthResource(JpaIdentityProvider jpaIdentityProvider, EntityManager entityManager) {
        this.jpaIdentityProvider = jpaIdentityProvider;
        this.entityManager = entityManager;
    }

    @POST
    public AuthResponse auth(AuthRequest request) {
        log.debug("Authentification en cours ...");

        UsernamePasswordAuthenticationRequest authRequest = new UsernamePasswordAuthenticationRequest(
            request.username(), // Username
            new PasswordCredential(request.password().toCharArray()) // Password
        );

        // Authentification de l'utilisateur
        SecurityIdentity identity = this.jpaIdentityProvider.authenticate(this.entityManager, authRequest);

        // Une fois qu'on a l'utilisateur authentifié, on peut générer le jeton JWT
        String jwt = Jwt.issuer("quest-quarkus-issuer")
            .upn(request.username()) // User Principal Name, le nom d'utilisateur
            .groups(identity.getRoles()) // Liste des rôles de l'utilisateur connecté, avec @Roles de @UserDefinition
            .sign()
        ;

        log.debug("Authentification réussie, jeton JWT généré !");

        return new AuthResponse(true, jwt);
    }
}
