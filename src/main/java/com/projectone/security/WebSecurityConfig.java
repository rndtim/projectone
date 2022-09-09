package com.projectone.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletResponse;

@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
  private final CustomUserDetailsService userDetailsService;
  private final JWTFilter jwtFilter;

  @Bean
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
            .csrf().disable()
            .exceptionHandling()
            .authenticationEntryPoint(
                    (request, response, authException) ->
                            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Error: Unauthorized")
            )
            .and()
            .authorizeRequests()
            .antMatchers("/", "/api/auth/registration", "/api/auth/login", "/api/posts").permitAll()
            .antMatchers("/api/users/**", "/api/posts/**").hasAnyRole("USER", "ADMIN")
            .anyRequest().authenticated()
            .and()
              .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
              .logout().permitAll()
            .and()
              .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

  }

  @Bean
  public PasswordEncoder getPasswordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailsService).passwordEncoder(getPasswordEncoder());
  }


}