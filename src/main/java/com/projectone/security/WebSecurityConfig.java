package com.projectone.security;

import com.projectone.api.services.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
  private final CustomUserDetailsService userDetailsService;

  public WebSecurityConfig(CustomUserDetailsService userDetailsService) {
    this.userDetailsService = userDetailsService;
  }


  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
            .authorizeRequests()
              .antMatchers("/", "/users/registration").permitAll()
              .anyRequest().authenticated()
            .and()
              .formLogin()
//              .loginPage("/login")
                .permitAll()
            .and()
              .logout()
                .permitAll();
  }

  @Bean
  DaoAuthenticationProvider daoAuthenticationProvider() {
    DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
    authenticationProvider.setPasswordEncoder(getPasswordEncoder());
    authenticationProvider.setUserDetailsService(userDetailsService);
    return authenticationProvider;
  }

  @Bean
  public PasswordEncoder getPasswordEncoder() {
    return new BCryptPasswordEncoder();
  }

//  @Override
//  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//    auth.userDetailsService(userDetailsService)
//            .passwordEncoder(NoOpPasswordEncoder.getInstance());
//  }
}