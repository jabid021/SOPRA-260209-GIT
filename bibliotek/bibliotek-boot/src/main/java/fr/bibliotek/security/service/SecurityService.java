package fr.bibliotek.security.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import fr.bibliotek.api.request.AuthRequest;
import fr.bibliotek.api.response.AuthResponse;
import fr.bibliotek.security.jwt.JwtUtil;

@Service
public class SecurityService {
    private final static Logger log = LoggerFactory.getLogger(SecurityService.class);
    private final AuthenticationManager authenticationManager;

    public SecurityService(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    public AuthResponse auth(AuthRequest authRequest) {
        try {
            log.debug("Tentative d'authentification ...");

            Authentication authentication = this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);

            log.debug("Authentification validée !");

            return new AuthResponse(true, JwtUtil.generate(authentication));
        }

        catch (BadCredentialsException ex) {
            log.error("Impossible de s'authentifier : mauvais identifiants.");
        }

        catch (Exception ex) {
            log.error("Impossible de s'authentifier : erreur ({}).", ex.getClass().getSimpleName());
        }

        return new AuthResponse(false, "");
    }
}
