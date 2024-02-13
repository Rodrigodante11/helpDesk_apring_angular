package com.rodrigo.helpdesk.secutity;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTUtil {

    @Value("${jwt.expiration}")
    private Long expiration;

    @Value("${jwt.secret}")
    private String secreteKey;


    public String generateToken(String email){
        return Jwts.builder()
                .setSubject(email)
                .setExpiration(new Date(System.currentTimeMillis() + expiration)) // expiricao data atual mais o tempo de expiracao
                .signWith(SignatureAlgorithm.ES512, secreteKey.getBytes())
                .compact();

    }

}
