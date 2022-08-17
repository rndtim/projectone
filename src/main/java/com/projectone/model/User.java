package com.projectone.model;

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

  public static User toModel(UserEntity userEntity) {
    User user = new User();
    user.setId(userEntity.getUserId());
    user.setName(userEntity.getName());
    user.setEmail(user.getEmail());
    user.setPosts(userEntity.getUserPosts().stream().map(Post::toModel).collect(Collectors.toList()));
    return user;
  }
}
