// MARTIN

package biblio.security;

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

import biblio.dao.IDAOUtilisateur;
import biblio.model.Utilisateur;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtHeaderFilter extends OncePerRequestFilter 
{
    @Autowired
    private IDAOUtilisateur daoUtilisateur;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException 
    {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) 
        {
            String token = authHeader.substring(7);
            Optional<String> optUsername = JwtUtils.validate(token);
            if (optUsername.isPresent()) 
            {
                String username = optUsername.get();
                Utilisateur utilisateur = this.daoUtilisateur.findByUsername(username).orElseThrow();
                List<GrantedAuthority> authorities = new ArrayList<>();
                if (utilisateur.isAdmin()) 
                {
                    authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
                }
                authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(username, null, authorities);
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }
        filterChain.doFilter(request, response);
    }
}
