package com.projectone.security;

import com.projectone.api.services.CustomUserDetailsService;
import com.projectone.filter.CustomAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
  private final CustomUserDetailsService userDetailsService;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
            .csrf().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
              .authorizeRequests()
                .antMatchers("/", "/users/registration").permitAll()
                .anyRequest().authenticated()
            .and()
              .addFilter(new CustomAuthenticationFilter(authenticationManagerBean()))
              .formLogin()
//              .loginPage("/login")
                .permitAll()
            .and()
              .logout()
                .permitAll();
  }

  @Bean
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailsService).passwordEncoder(getPasswordEncoder());
  }

  @Bean
  public PasswordEncoder getPasswordEncoder() {
    return new BCryptPasswordEncoder();
  }

}