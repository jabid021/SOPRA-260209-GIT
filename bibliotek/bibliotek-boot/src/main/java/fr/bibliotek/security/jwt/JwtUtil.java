package fr.bibliotek.security.jwt;

import java.util.Date;
import java.util.Optional;

import javax.crypto.SecretKey;

import org.springframework.security.core.Authentication;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class JwtUtil {
    private static final String KEY = "19D6IjLAudjoZMxFHnp/Owq2SKapi7JRqGhUo82TrAMF9JBz7ATG4SnDLulvQqI2";

    private JwtUtil() { }

    public static String generate(Authentication authentication) {
        // Création de la clé de signature
        SecretKey key = Keys.hmacShaKeyFor(KEY.getBytes());
        Date now = new Date();

        return Jwts.builder()
            .subject(authentication.getName())
            .issuedAt(now)
            .expiration(new Date(now.getTime() + 36_000_000))
            .signWith(key)
            .compact()
        ;
    }

    public static Optional<String> getUsername(String token) {
        try {
            SecretKey key = Keys.hmacShaKeyFor(KEY.getBytes());

            return Optional.ofNullable(
                Jwts.parser()
                    .verifyWith(key)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload()
                    .getSubject()
            );
        }

        catch (Exception ex) {
            return Optional.empty();
        }
    }
}
