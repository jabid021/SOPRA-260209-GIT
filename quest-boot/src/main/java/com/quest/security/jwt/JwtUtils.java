package com.quest.security.jwt;

import java.util.Optional;

import org.jose4j.jws.JsonWebSignature;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.consumer.JwtConsumer;
import org.jose4j.jwt.consumer.JwtConsumerBuilder;
import org.jose4j.keys.HmacKey;
import org.jose4j.lang.JoseException;
import org.springframework.security.core.Authentication;

public class JwtUtils {
     private final static String JWT_KEY = "6E5A7234753778214125442A472D4B6150645367556B58703273357638792F42";
    private final static long JWT_EXPIRATION = 3_600_000; // 1 heure

    private JwtUtils() { }

    public static String generate(Authentication auth) {
        JwtClaims claims = new JwtClaims();
        JsonWebSignature jws = new JsonWebSignature();

        claims.setSubject(auth.getName());
        claims.setIssuedAtToNow();
        claims.setExpirationTimeMinutesInTheFuture((int)(JWT_EXPIRATION / 60000));

        jws.setPayload(claims.toJson());
        jws.setAlgorithmHeaderValue("HS256");
        jws.setKey(new HmacKey(JWT_KEY.getBytes()));

        try {
            return jws.getCompactSerialization();
        }

        catch (JoseException e) {
            return "";
        }
    }

    public static Optional<String> validate(String token) {
        try {
            JwtConsumer consumer = new JwtConsumerBuilder()
                .setVerificationKey(new HmacKey(JWT_KEY.getBytes()))
                .setRequireExpirationTime()
                .build();

            JwtClaims claims = consumer.processToClaims(token);
            return Optional.ofNullable(claims.getSubject());
        }

        catch (Exception ex) {
            return Optional.empty();
        }
    }
}
