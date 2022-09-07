package com.projectone.api.dto;

import com.projectone.store.entities.UserEntity;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class User {
  private Long id;
  private String username;
  private String email;
  private List<Post> posts;

  public static User convertEntityToModel(UserEntity userEntity) {
    User user = new User();
    user.setId(userEntity.getId());
    user.setUsername(userEntity.getUsername());
    user.setEmail(userEntity.getEmail());
    user.setPosts(userEntity.getUserPosts().stream().map(Post::convertEntityToModel).collect(Collectors.toList()));
    return user;
  }
}