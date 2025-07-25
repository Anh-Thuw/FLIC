package com.flic.courseRegister.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {
    private final String SECRET_KEY = "toiyeuvekaucuatoibanthisaotoicungyeuvekau";
    private final long EXPIRATION_TIME = 24 * 60 * 60 * 1000; //24h

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    public String generateToken(String email, String role) {
        return Jwts.builder()
                .setSubject(email)
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractUsername(String token) {
        String username = parseToken(token).getBody().getSubject();
        System.out.println(" [JwtUtil] extractUsername = " + username);
        return username;
    }

    public boolean isTokenValid(String token, String username) {
        final String extractedUsername = extractUsername(token);
        boolean valid = extractedUsername.equals(username) && !isTokenExpired(token);
        System.out.println(" [JwtUtil] isTokenValid = " + valid);
        return valid;
    }


    private boolean isTokenExpired(String token) {
        return parseToken(token).getBody().getExpiration().before(new Date());
    }

    public Jws<Claims> parseToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token);
    }

}
