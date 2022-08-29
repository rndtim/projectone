package com.projectone.api.services;

import com.projectone.api.exceptions.NoSuchUser;
import com.projectone.store.entities.UserEntity;
import com.projectone.store.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
  private final UserRepository userRepository;

  public CustomUserDetailsService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    UserEntity userEntity = userRepository.findUserByEmail(email);
    if (userEntity == null)
      throw new NoSuchUser("No user with this email");
    return userRepository.findUserByEmail(email);
  }
}
