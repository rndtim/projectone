package com.projectone.security;
//
//  provides methods for generating, parsing, validating JWT
//

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.projectone.api.dto.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.stream.Collectors;

@Component
public class JWTUtil {

  @Value("${jwt.secret}")
  private String secret;

  private final long expirationMs = 10 * 60 * 1000;

  // generate a JWT
  public String generateToken(User user) throws IllegalArgumentException, JWTCreationException {
    return JWT.create()
            .withSubject(user.getUsername())
            .withExpiresAt(new Date(System.currentTimeMillis() + expirationMs))
            .withIssuer("ProjectOne")
            .withClaim("roles", user.getEmail())
            .sign(Algorithm.HMAC256(secret));
  }

  public DecodedJWT validateToken(String token) throws JWTVerificationException {
    JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret)).build();
    return verifier.verify(token);
  }
}