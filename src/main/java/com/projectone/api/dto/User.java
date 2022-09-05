package com.projectone.api.dto;

import com.projectone.store.entities.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
  private Long id;
  private String name;
  private String email;
  private List<Post> posts;

  // This method can be realized in Factory(@Component) directory
  // as a public method (entity) with Builder return DTO
  public static User convertEntityToModel(UserEntity userEntity) {
    User user = new User();
    user.setId(userEntity.getId());
    user.setName(userEntity.getUsername());
    user.setEmail(userEntity.getEmail());
    user.setPosts(userEntity.getUserPosts().stream().map(Post::convertEntityToModel).collect(Collectors.toList()));
    return user;
  }
}
