package com.itheima.tlias.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Date;
import java.util.Map;

public class JwtUtils {
    private static final String SECRET = "S/4AN9IsSRUC~{0c4]y#$F2XbV8^`#a14vawn<~Kr@(D%3TF-p1s/h{Y9k7y((rR";
    private static final SecretKey signKey = Jwts.SIG.HS256.key()
            .random(new SecureRandom(SECRET.getBytes(StandardCharsets.UTF_8)))
            .build();
    private static final Long expire = 43200000L;

    /**
     * generateJwt
     * @param claims
     * @return
     */
    public static String generateJwt(Map<String, Object> claims){
        return Jwts.builder()
                .claims(claims)
                .signWith(signKey)
                .expiration(new Date(System.currentTimeMillis() + expire))
                .compact();
    }

    /**
     * parse JWT
     * @param jwt
     * @return
     */
    public static Claims parseJwt(String jwt){
        return Jwts.parser()
                .verifyWith(signKey)
                .build()
                .parseSignedClaims(jwt)
                .getPayload();
    }
}
