package com.projectone.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.stream.Collectors;

public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
  private final AuthenticationManager authenticationManager;

  public CustomAuthenticationFilter(AuthenticationManager authenticationManager) {
    this.authenticationManager = authenticationManager;
  }

  @Override
  public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
    String username = request.getParameter("username");
    String password = request.getParameter("password");
    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
    return authenticationManager.authenticate(authenticationToken);
  }

  //if authentication successful then will be called this method
  @Override
  protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {
    User user = (User) authentication.getPrincipal(); // spring security User, not Entity
    Environment env = getEnvironment();
    String SECRET_KEY = env.getProperty("jwt.secret");
    Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);

    String access_token = JWT.create()
            .withSubject(user.getUsername())
            .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000)) // now + 10min
            .withIssuer(request.getRequestURL().toString()) // "author" of the token
            .withClaim("roles", user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
            .sign(algorithm);

    String refresh_token = JWT.create()
            .withSubject(user.getUsername())
            .withExpiresAt(new Date(System.currentTimeMillis() + 30 * 60 * 1000)) // now + 30min
            .withIssuer(request.getRequestURL().toString()) // "author" of the token
            .sign(algorithm);
    response.setHeader("access_token", access_token);
    response.setHeader("refresh_token", refresh_token);
  }
}
