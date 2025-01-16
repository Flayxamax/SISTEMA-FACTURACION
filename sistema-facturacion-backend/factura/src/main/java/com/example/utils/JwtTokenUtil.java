package com.example.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.util.Date;
import java.security.Key;

public class JwtTokenUtil {
    @SuppressWarnings("deprecation")
    public static final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS512);

    @SuppressWarnings("deprecation")
    public static String generateToken(Long facturaId, int expirationInMinutes) {
        return Jwts.builder()
                .setSubject(facturaId.toString())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationInMinutes * 60 * 1000))
                .signWith(SECRET_KEY)
                .compact();
    }
}
