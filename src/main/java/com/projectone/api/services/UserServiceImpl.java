package com.projectone.api.services;

import com.projectone.api.exceptions.NoSuchUser;
import com.projectone.api.exceptions.UserAlreadyExists;
import com.projectone.api.dto.User;
import com.projectone.store.entities.Roles;
import com.projectone.store.entities.UserEntity;
import com.projectone.store.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  public List<User> getAllUsers() {
    return userRepository.findAll().stream().map(User::convertEntityToModel).collect(Collectors.toList());
  }

  public User save(UserEntity userEntity) {
    try {
      if (userRepository.findByUsername(userEntity.getUsername()) != null) {
        throw new UserAlreadyExists("This username is being already used");
      }
      if (userRepository.findByEmail(userEntity.getEmail()) != null) {
        throw new UserAlreadyExists("This email is being already used");
      }
      userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
      userEntity.setActive(true);
      userEntity.getRoles().add(Roles.ROLE_USER);
      return User.convertEntityToModel(userRepository.save(userEntity));
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  public User getUserByUsername(String username) {
    try {
      if (userRepository.findByUsername(username) == null) throw new NoSuchUser("No user with this username");
      return User.convertEntityToModel(userRepository.findByUsername(username));
    } catch (NoSuchUser e) {
      throw new RuntimeException(e);
    }
  }

  public void deleteUserById(Long userId) {
    userRepository.deleteById(userId);
  }
}
