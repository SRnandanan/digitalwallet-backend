package com.os.digitalwallet.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Data
public class JwtUtil {
    @Value("${jwt.key}")
    private String secretKey;



    public String generateToken(String username){
        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .signWith(SignatureAlgorithm.HS256, JwtHelper.getSecretKey().trim())
                .compact();
    }

    public static String extractUsername(String token){
        return Jwts.parser()
                .setSigningKey(JwtHelper.getSecretKey().trim())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    public static boolean validateToken(String token, UserDetails user){
        return extractUsername(token).equals(user.getUsername());
    }

}
