package com.quest.security.jwt;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.quest.dao.IDAOPersonne;
import com.quest.model.Personne;
import com.quest.model.Stagiaire;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtHeaderFilter extends OncePerRequestFilter {
    @Autowired
    private IDAOPersonne daoPersonne;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");

        System.out.println("Contenu Authorization : " + authHeader);

        if (authHeader != null) {
            String token = authHeader.substring(7);

            System.out.println("Token = " + token);

            Optional<String> optUsername = JwtUtils.validate(token);

            if (optUsername.isPresent()) {
                String username = optUsername.get();
                System.out.println("jeton valide, user = " + username);

                Personne personne = this.daoPersonne.findByLogin(username).orElseThrow();

                List<GrantedAuthority> authorities = new ArrayList<>();

                if (personne instanceof Stagiaire) {
                    authorities.add(new SimpleGrantedAuthority("ROLE_STAGIAIRE"));
                }

                else {
                    authorities.add(new SimpleGrantedAuthority("ROLE_FORMATEUR"));
                }

                // Recréer une Authentication Spring Security
                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(username, null, authorities);

                // Affecter l'authentication dans le contexte de Spring Security -> lui dire OK, l'utilisateur est authentifié !
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }

        // Pour passer à la suite
        filterChain.doFilter(request, response);
    }
}
