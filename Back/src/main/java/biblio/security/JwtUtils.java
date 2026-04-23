// MARTIN

package biblio.security;

import java.util.Date;
import java.util.Optional;
import javax.crypto.SecretKey;
import org.springframework.security.core.Authentication;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class JwtUtils 
{
    private static final String JWT_KEY = "biblio-secret-key-2026-change-in-prod!!";
    private static final long EXPIRATION_MS = 172_800_000;

    private JwtUtils() {}

    public static String generate(Authentication auth) 
    {
        Date now = new Date();
        SecretKey secretKey = Keys.hmacShaKeyFor(JWT_KEY.getBytes());
        return Jwts.builder()
                .subject(auth.getName())
                .issuedAt(now)
                .expiration(new Date(now.getTime() + EXPIRATION_MS))
                .signWith(secretKey)
                .compact();
    }

    public static Optional<String> validate(String token) 
    {
        SecretKey secretKey = Keys.hmacShaKeyFor(JWT_KEY.getBytes());
        try 
        {
            return Optional.of
            (
                Jwts.parser()
                    .verifyWith(secretKey)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload()
                    .getSubject()
            );
        } 
        catch (Exception ex) 
        {
            return Optional.empty();
        }
    }
}
