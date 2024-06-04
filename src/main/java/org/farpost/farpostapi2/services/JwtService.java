package org.farpost.farpostapi2.services;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.farpost.farpostapi2.dto.admin_dto.AdminDto;
import org.farpost.farpostapi2.exceptions.security.BadAuthSwaggerRequestException;
import org.farpost.farpostapi2.exceptions.security.SwaggerAccessExpiredException;
import org.farpost.farpostapi2.services.utils.JwtClaim;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.SecretKeySpec;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class JwtService {

    @Value("${swagger.secret-key}")
    private String swaggerKey;

    @Value("${swagger.expired}")
    private Long swaggerExpired;

    private SecretKey swaggerSecretKey;

    @PostConstruct
    void init(){
        swaggerSecretKey = Keys.hmacShaKeyFor(swaggerKey.getBytes());
    }


    public String swaggerJwtCreator(Map<String, Object> claims){
        Instant now = Instant.now();
        Instant expired = now.plusMillis(swaggerExpired);
        Claims tokenClaims = Jwts.claims().add(claims).build();
        return Jwts.builder()
                .claims(tokenClaims)
                .expiration(Date.from(expired))
                .issuedAt(Date.from(now))
                .signWith(swaggerSecretKey)
                .compact();
    }

    public Authentication parseSwaggerToken(String accessToken) {
        try {
            Jws<Claims> claims = Jwts.parser().verifyWith(swaggerSecretKey).build().parseSignedClaims(accessToken);
            String username = claims.getPayload().get(JwtClaim.USERNAME.getName(), String.class);
            String role = claims.getPayload().get(JwtClaim.ROLE.getName(), String.class);
            GrantedAuthority authority = new SimpleGrantedAuthority(role);
            return new UsernamePasswordAuthenticationToken(username, "", List.of(authority));
        } catch (JwtException e) {
            if (e instanceof ExpiredJwtException)
                throw new SwaggerAccessExpiredException();
            else {
                log.error(e.getMessage());
                throw new BadAuthSwaggerRequestException();
            }
        }
    }


}
