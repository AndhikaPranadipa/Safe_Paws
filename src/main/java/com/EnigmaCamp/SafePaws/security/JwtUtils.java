package com.EnigmaCamp.SafePaws.security;

import com.EnigmaCamp.SafePaws.entity.Customer;
import com.EnigmaCamp.SafePaws.entity.Shelter;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtils {

    private final String jwtSignatureKey = "final-project-incubation-2.0-spring-boot-25-1";

    private final int jwtExpInMs = 1000 * 60 * 60;

    public SecretKey getSignKey(){
        return Keys.hmacShaKeyFor(jwtSignatureKey.getBytes());
    }

    public String generateTokenKeyCustomer(Customer request){
        Date currentDate = new Date();
        Date expDate = new Date(currentDate.getTime() + jwtExpInMs);
        return Jwts.builder()
                .subject(request.getEmail())
                .issuedAt(currentDate)
                .expiration(expDate)
                .signWith(getSignKey())
                .compact();
    }

    public String generateTokenKeyShelter(Shelter request){
        Date currentDate = new Date();
        Date expDate = new Date(currentDate.getTime() + jwtExpInMs);
        return Jwts.builder()
                .subject(request.getEmail())
                .issuedAt(currentDate)
                .expiration(expDate)
                .signWith(getSignKey())
                .compact();
    }

    public Claims decodeTokenJwt(String ApiToken){
        return Jwts.parser()
                .verifyWith(getSignKey())
                .build()
                .parseSignedClaims(ApiToken)
                .getPayload();
    }
}

