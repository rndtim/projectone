package com.projectone.services;

import com.projectone.entities.UserEntity;
import com.projectone.exceptions.NoSuchUser;
import com.projectone.repositories.UserRepository;
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
