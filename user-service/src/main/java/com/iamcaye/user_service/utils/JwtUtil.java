package com.iamcaye.user_service.utils;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.iamcaye.user_service.models.User;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtUtil {
    @Value("${security.jwt.secret-key}")
    private static String secretKey;

    @Value("${security.jwt.expiration-time}")
    private static long jwtExpiration;

    public JwtUtil(
        @Value("${security.jwt.secret-key}") String secret,
        @Value("${security.jwt.expiration-time}") long expiration
    ) {
        System.out.println(secret);
        JwtUtil.secretKey = secret;
        JwtUtil.jwtExpiration = expiration;
    }

    public static String generateToken(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("user", user);

        String token = Jwts.builder()
            .setClaims(claims)
            .setSubject(user.getUsername())
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis() + jwtExpiration))
            .signWith(getSignInKey(), SignatureAlgorithm.HS256)
            .compact();
        return token;
    }

    private static Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
