package com.quest.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.quest.dao.IDAOPersonne;
import com.quest.dto.request.AuthRequest;
import com.quest.dto.request.SubscriptionRequest;
import com.quest.dto.response.AuthResponse;
import com.quest.dto.response.EntityCreatedOrUpdatedResponse;
import com.quest.model.Stagiaire;
import com.quest.security.jwt.JwtUtils;

import jakarta.validation.Valid;

@RestController
public class SecurityApiController {
    private final static Logger log = LoggerFactory.getLogger(SecurityApiController.class);
    private final AuthenticationManager authenticationManager;
    private final IDAOPersonne daoPersonne;
    private final PasswordEncoder passwordEncoder;

    public SecurityApiController(AuthenticationManager authenticationManager, IDAOPersonne daoPersonne, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.daoPersonne = daoPersonne;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/api/auth")
    public AuthResponse auth(@Valid @RequestBody AuthRequest request) {
        try {
            log.debug("Tentative d'authentification ...");

            Authentication authentication = this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);

            log.debug("Authentification validée !");

            return new AuthResponse(true, JwtUtils.generate(authentication));
        }

        catch (BadCredentialsException ex) {
            log.error("Authentification impossible : mauvais identifiants.");
        }

        catch (Exception ex) {
            log.error("Authentification impossible : erreur ({}).", ex.getClass().getSimpleName());
        }

        return new AuthResponse(false, "");
    }


    @PostMapping("/api/inscription")
    public EntityCreatedOrUpdatedResponse subscribe(@Valid @RequestBody SubscriptionRequest request) {
        Stagiaire stagiaire = new Stagiaire();

        stagiaire.setLogin(request.getUsername());
        stagiaire.setPassword(this.passwordEncoder.encode(request.getPassword()));

        stagiaire.setNom(request.getNom());
        stagiaire.setPrenom(request.getPrenom());
        stagiaire.setCivilite(request.getCivilite());

        this.daoPersonne.save(stagiaire);

        return new EntityCreatedOrUpdatedResponse(stagiaire.getId());
    }
}
