package com.calmify.backend.service.impl;

import com.calmify.backend.service.JwtService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
@Service
public class JwtServiceImpl implements JwtService {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private long expiration;

    @Override
    public String generateToken(String email) {

        System.out.println("INSIDE GENERATE TOKEN");
        System.out.println("EMAIL = " + email);
        System.out.println("SECRET = " + secret);


        SecretKey key = Keys.hmacShaKeyFor(secret.getBytes());

        return Jwts.builder()
                .subject(email)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(key)
                .compact();

    }
    @Override
public String extractEmail(String token) {

    SecretKey key = Keys.hmacShaKeyFor(secret.getBytes());

    Jws<Claims> claims = Jwts.parser()
            .verifyWith(key)
            .build()
            .parseSignedClaims(token);

    return claims.getPayload().getSubject();
}
/* 
@Override
public boolean isTokenValid(String token) {

    try {
        extractEmail(token);
        return true;
    } catch (Exception e) {
        return false;
    }
}
    */
@Override
public boolean isTokenValid(String token) {

    try {
        

       extractEmail(token);

        return true;

    } catch (Exception e) {   

        return false;
    }
}
}
