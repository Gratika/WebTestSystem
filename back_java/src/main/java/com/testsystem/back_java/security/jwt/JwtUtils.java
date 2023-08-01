package com.testsystem.back_java.security.jwt;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWT;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.JWTParser;
import com.nimbusds.jwt.SignedJWT;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.text.ParseException;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@Slf4j
public class JwtUtils {

    @Value("${jwt.token.secret}")
    private String jwtSecret;

    @Value("${jwt.token.expired}")
    private int jwtExpirationMs;

    public String generateJwtToken(Authentication authentication) throws JOSEException {

        SecureRandom random = new SecureRandom();
        byte[] sharedSecret = new byte[32];
        random.nextBytes(sharedSecret);

        UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();

        // Получаем id пользователя, пароль и роли из UserDetailsImpl
        String userId = userPrincipal.getId().toString();
        String password = userPrincipal.getPassword();
        Collection<? extends GrantedAuthority> authorities = userPrincipal.getAuthorities();
        List<String> roles = authorities.stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        JWTClaimsSet claims = new JWTClaimsSet.Builder()
                .subject(userPrincipal.getUsername())
                .claim("userId", userId)
                .claim("password", password)
                .claim("roles", roles)
                .issueTime(new Date())
                .expirationTime(new Date(System.currentTimeMillis() + jwtExpirationMs))
                .build();
        JWSHeader jwsHeader =
                new JWSHeader.Builder(JWSAlgorithm.HS256).build();

        JWSSigner signer = new MACSigner(sharedSecret);;
        SignedJWT signedJWT = new SignedJWT(jwsHeader, claims);
        signedJWT.sign(signer);

        return signedJWT.serialize();
    }

    public Boolean isTokenExpired(String token) {
        Date expiration = getExpirationDateFromToken(token);
        return expiration != null && expiration.before(new Date());
    }

    public Date getExpirationDateFromToken(String token) {
        try {
            JWT jwt = JWTParser.parse(token);
            return jwt.getJWTClaimsSet().getExpirationTime();
        } catch (ParseException e) {
            log.error("Failed to parse JWT token: {}", e.getMessage());
            return null;
        }
    }

    public String getUserNameFromJwtToken(String token) {
        try {
            JWT jwt = JWTParser.parse(token);
            return jwt.getJWTClaimsSet().getSubject();
        } catch (ParseException e) {
            log.error("Failed to parse JWT token: {}", e.getMessage());
            return null;
        }
    }

    public <T> T getClaimFromToken(String token, Function<JWTClaimsSet, T> claimsResolver) {
        try {
            JWT jwt = JWTParser.parse(token);
            return claimsResolver.apply(jwt.getJWTClaimsSet());
        } catch (ParseException e) {
            log.error("Failed to parse JWT token: {}", e.getMessage());
            return null;
        }
    }

    public boolean validateJwtToken(String authToken) {
        try {
            JWT jwt = JWTParser.parse(authToken);
            return !jwt.getJWTClaimsSet().getExpirationTime().before(new Date());

        } catch (ParseException e) {
            log.error("Failed to parse JWT token: {}", e.getMessage());
            return false;
        }
    }

}


/*import io.jsonwebtoken.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.function.Function;

@Component
@Slf4j
public class JwtUtils {

    @Value("${jwt.token.secret}")
    private String jwtSecret;

    @Value("${jwt.token.expired}")
    private int jwtExpirationMs;

    public String generateJwtToken(Authentication authentication) {

        UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();

        return Jwts.builder()
                .setSubject((userPrincipal.getUsername()))
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
               .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }


    //перевірити, чи закінчився термін дії токена
    public Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }
    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }
    public String getUserNameFromJwtToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }
    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }
    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
    }


    public boolean validateJwtToken(String authToken) {

        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);

            return !claims.getBody().getExpiration().before(new Date());
        } catch (JwtException | IllegalArgumentException e) {
            log.error("JWT token is expired or invalid: {}",e.getMessage());
            throw new JwtAuthenticationException("JWT token is expired or invalid");
        }
    }*/



