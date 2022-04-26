package com.demo.shop.web.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtils {
    private static final String KEY = "TOKEN";

    public String generateToken(UserDetails userDetails){
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(SignatureAlgorithm.HS256, KEY).compact();
    }

    /**
     * Validador del token que se envia y del usuario de aplicacion
     * @param token
     * @param userDetails
     * @return
     */
    public boolean validatorJwt(String token, UserDetails userDetails){
        return userDetails.getUsername().equals(extractUserFromToken(token)) && !isTokenExpired(token);
    }

    public String extractUserFromToken(String token){
        return getClaims(token).getSubject();
    }

    public boolean isTokenExpired(String token){
        return getClaims(token).getExpiration().before(new Date());
    }

    /**
     * El proposito es deserializar el token y obtener el objeto enviado
     * @param token
     * @return
     */
    private Claims getClaims(String token){
        return  Jwts.parser().setSigningKey(KEY).parseClaimsJws(token).getBody();
    }
}
