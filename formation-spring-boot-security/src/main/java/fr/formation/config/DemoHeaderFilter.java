package fr.formation.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class DemoHeaderFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String ajcHeader = request.getHeader("Authorization");

        System.out.println("DEMONSTRATION FILTRE : " + ajcHeader);

        // GrantedAuthority => Classe d'autorisation Spring Security
        List<GrantedAuthority> authorities = new ArrayList<>();

        authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));

        // Authentification Spring Security => Utilisateur connecté, avec son nom, et sa liste d'autorisations
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken("un username", null, authorities);

        // On récupère le contexte de Spring Security, et on injecte l'Authentification qu'on vient de créer : on simule une connexion OK
        SecurityContextHolder.getContext().setAuthentication(auth);

        // ATTENTION, obligatoire pour passer à la suite
        filterChain.doFilter(request, response);
    }
}
