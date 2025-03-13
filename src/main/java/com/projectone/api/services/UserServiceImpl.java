package com.projectone.api.services;

import com.projectone.api.dto.Post;
import com.projectone.api.dto.User;
import com.projectone.api.exceptions.NoSuchUser;
import com.projectone.api.exceptions.UserAlreadyExists;
import com.projectone.store.entities.PostEntity;
import com.projectone.store.entities.Roles;
import com.projectone.store.entities.UserEntity;
import com.projectone.store.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll().stream().map(User::convertEntityToDTO).collect(Collectors.toList());
    }

    public UUID save(User user) {
        try {
            if (userRepository.findByUsername(user.getUsername()) != null) {
                throw new UserAlreadyExists("This username is being already used");
            }
            if (userRepository.findByEmail(user.getEmail()) != null) {
                throw new UserAlreadyExists("This email is being already used");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        UserEntity userEntity = UserEntity.builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .roles(List.of(Roles.ROLE_USER))
                .build();
        return userRepository.save(userEntity).getId();
    }

    public User getByUsername(String username) {
        try {
            if (userRepository.findByUsername(username) == null) throw new NoSuchUser("No user with this username");
            return User.convertEntityToDTO(userRepository.findByUsername(username));
        } catch (NoSuchUser e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteUserById(UUID userId) {
        userRepository.deleteById(userId);
    }
}
