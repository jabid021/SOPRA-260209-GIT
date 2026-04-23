// MARTIN

package biblio.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import biblio.dto.AuthRequest;
import biblio.dto.AuthResponse;
import biblio.security.JwtUtils;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class SecurityApiController 
{
    private static final Logger log = LoggerFactory.getLogger(SecurityApiController.class);

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/api/auth")
    public AuthResponse auth(@RequestBody AuthRequest request) 
    {
        try 
        {
            log.debug("Tentative d'authentification pour : {}", request.getUsername());
            Authentication authentication = this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            log.debug("Authentification validée !");
            return new AuthResponse(true, JwtUtils.generate(authentication));

        } 
        catch (BadCredentialsException ex) 
        {
            log.error("Authentification impossible : mauvais identifiants.");
        } 
        catch (Exception ex) 
        {
            log.error("Authentification impossible : erreur ({}).", ex.getClass().getSimpleName());
        }
        return new AuthResponse(false, "");
    }
}
