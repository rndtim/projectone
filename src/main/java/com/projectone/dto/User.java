package com.projectone.dto;

import com.projectone.entities.UserEntity;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class User {
  private Long id;
  private String name;
  private String email;
  private List<Post> posts;

  public static User convertEntityToModel(UserEntity userEntity) {
    User user = new User();
    user.setId(userEntity.getUserId());
    user.setName(userEntity.getUsername());
    user.setEmail(userEntity.getEmail());
    user.setPosts(userEntity.getUserPosts().stream().map(Post::convertEntityToModel).collect(Collectors.toList()));
    return user;
  }
}
