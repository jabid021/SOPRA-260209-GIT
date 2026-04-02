package fr.formation.controller;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.formation.config.JwtUtils;

@RestController
@RequestMapping("/api")
public class UtilisateurController {
    @PostMapping("/auth")
    public String auth() {
        // On authentifie l'utilisateur ...

        UsernamePasswordAuthenticationToken simulation = new UsernamePasswordAuthenticationToken("username", "password");

        return JwtUtils.generate(simulation);
    }
}
