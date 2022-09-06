package com.projectone.api.services;

import com.projectone.api.exceptions.NoSuchUser;
import com.projectone.store.entities.UserEntity;
import com.projectone.store.repositories.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class CustomUserDetailsService implements UserDetailsService {
  private final UserRepository userRepository;

  public CustomUserDetailsService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    UserEntity user = userRepository.findByUsername(email);
    if (user == null)
      throw new NoSuchUser("No user with this username");
    Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
    user.getRoles().forEach(role -> {
      authorities.add(new SimpleGrantedAuthority(role.toString()));
    });
    return new User(user.getUsername(), user.getPassword(), authorities);
  }
}
