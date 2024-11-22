   package com.snazzyrobot.molviewbackend.security;

   import com.auth0.jwt.JWT;
   import com.auth0.jwt.algorithms.Algorithm;
   import com.auth0.jwt.exceptions.JWTVerificationException;
   import com.auth0.jwt.interfaces.DecodedJWT;
   import com.auth0.jwt.interfaces.JWTVerifier;
   import org.springframework.beans.factory.annotation.Value;
   import org.springframework.stereotype.Component;

   import java.util.Date;

   @Component
   public class JwtUtil {

       @Value("${jwt.secret}")
       private String secretKey;

       @Value("${jwt.expiration}")
       private Long expiration;

       public String generateToken(String username) {
           Algorithm algorithm = Algorithm.HMAC512(secretKey);
           return JWT.create()
                   .withSubject(username)
                   .withIssuedAt(new Date())
                   .withExpiresAt(new Date(System.currentTimeMillis() + expiration * 1000))
                   .sign(algorithm);
       }

       public DecodedJWT extractClaims(String token) {
           try {
               Algorithm algorithm = Algorithm.HMAC512(secretKey);
               JWTVerifier verifier = JWT.require(algorithm).build();
               return verifier.verify(token);
           } catch (JWTVerificationException exception) {
               // Invalid token
               return null;
           }
       }

       public String extractUsername(String token) {
           DecodedJWT decodedJWT = extractClaims(token);
           return decodedJWT != null ? decodedJWT.getSubject() : null;
       }

       public Boolean isTokenExpired(String token) {
           DecodedJWT decodedJWT = extractClaims(token);
           return decodedJWT != null && decodedJWT.getExpiresAt().before(new Date());
       }
   }