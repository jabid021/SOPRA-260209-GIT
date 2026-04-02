package fr.formation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.formation.config.JwtUtils;
import fr.formation.dto.request.AuthRequest;
import fr.formation.dto.response.TokenResponse;

@RestController
@RequestMapping("/api")
public class UtilisateurController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/auth")
    public TokenResponse auth(@RequestBody AuthRequest request) {
        // On authentifie l'utilisateur ...
        Authentication authentication = new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword());

        authentication = this.authenticationManager.authenticate(authentication);

        return new TokenResponse(JwtUtils.generate(authentication));
    }
}
