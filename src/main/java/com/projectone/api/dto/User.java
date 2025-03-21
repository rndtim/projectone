package com.projectone.api.dto;

import com.projectone.store.entities.Roles;
import com.projectone.store.entities.UserEntity;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class User {
    private String username;
    private String email;
    private String password;
    private List<Post> posts;
    private List<Roles> roles;

    public static User convertEntityToDTO(UserEntity userEntity) {
        return User.builder()
                .username(userEntity.getUsername())
                .email(userEntity.getEmail())
                .password(userEntity.getPassword())
                .posts(userEntity.getUserPosts().stream().map(Post::convertEntityToDTO).collect(Collectors.toList()))
                .roles(userEntity.getRoles())
                .build();
    }
}
