package fr.bibliotek.security.jwt;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import fr.bibliotek.repo.UtilisateurRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtHeaderFilter extends OncePerRequestFilter {
    private final UtilisateurRepository repository;

    public JwtHeaderFilter(UtilisateurRepository repository) {
        this.repository = repository;
    }

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("Authorization");

        if (token != null) {
            token = token.substring(7);
            Optional<String> optUsername = JwtUtil.getUsername(token);

            if (optUsername.isPresent() && this.repository != null) {
                this.repository.findByUsername(optUsername.get()).orElseThrow();
                List<SimpleGrantedAuthority> authorities = new ArrayList<>();

                authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

                Authentication authentication = new UsernamePasswordAuthenticationToken(optUsername.get(), null, authorities);

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        // On passe au filtre suivant ...
        filterChain.doFilter(request, response);
    }
}
