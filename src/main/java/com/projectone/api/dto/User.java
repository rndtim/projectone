package com.projectone.api.dto;

import com.projectone.store.entities.Roles;
import com.projectone.store.entities.UserEntity;
import lombok.Data;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
public class User {
  private String username;
  private String email;
  private List<Post> posts;
  private List<Roles> roles;

  public static User convertEntityToDTO(UserEntity userEntity) {
    User user = new User();
    user.setUsername(userEntity.getUsername());
    user.setEmail(userEntity.getEmail());
    user.setPosts(userEntity.getUserPosts().stream().map(Post::convertEntityToDTO).collect(Collectors.toList()));
    user.setRoles(userEntity.getRoles());
    return user;
  }
}
