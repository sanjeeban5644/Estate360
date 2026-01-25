package com.sanjeeban.CoreApartmentService.service;


import com.sanjeeban.CoreApartmentService.entity.UserAccount;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class JwtService {

    @Value("${jwt.secretKey}")
    private String secretKey;

    //  create the token

    public String generateToken(UserAccount user){
        return Jwts.builder()
                .subject(user.getUserId().toString())
                .claim("UserName",user.getUserName())
                .claim("Email",user.getEmail())
                .claim("Status",user.getStatus())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60))
                .signWith(Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8)))
                .compact();
    }


    // verify the token

    // get the userid from the token
    public Long getUserIdFromToken(String token){
        Claims claims = Jwts.parser()
                .verifyWith(Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8)))
                .build()
                .parseSignedClaims(token)
                .getPayload();

        return Long.valueOf(claims.getSubject());

    }


}
