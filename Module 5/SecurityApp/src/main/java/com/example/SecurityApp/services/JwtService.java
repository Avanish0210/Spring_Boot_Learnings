package com.example.SecurityApp.services;

import com.example.SecurityApp.entities.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Set;

@Service
public class JwtService {

    @Value("${jwt.secretKey}")
    private String jwtSecretKey;
    //two methods only first to create jwt token and seconf one is to verify jwt token
    //for jwt token you should have your user

    private SecretKey getSecretKey() {     //way to make the secret key
        return Keys.hmacShaKeyFor(jwtSecretKey.getBytes(StandardCharsets.UTF_8));
    }

    //this function return token
    public String generateToken(User user) {
        return Jwts.builder()
                .subject(user.getId().toString()) // from user we get the id
                .claim("email" , user.getEmail()) //claims are key value pair you can pass claim as many
                .claim("roles" , Set.of("Admin" , "USER"))
                .issuedAt(new Date())  // we define the date we created so that we can have its expiry
                .expiration(new Date(System.currentTimeMillis() + 1000*60))   //way to write expiry
                //after that we have to create a secret key in application properties and then implement here above and then make it as a secret key above i did
                .signWith(getSecretKey())  //then we do sign in
                .compact(); //then we return it as a compact
    }

    public Long getUserIdFromToken(String token) {
        //we have to decode this token
        Claims claims = Jwts.parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
                //this payloads contain all the claims
        return Long.valueOf(claims.getSubject());
    }
}
