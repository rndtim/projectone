package com.projectone.api.dto;

import com.projectone.store.entities.Roles;
import com.projectone.store.entities.UserEntity;
import lombok.Data;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
public class Author {
  private Long id;
  private String username;
  private Set<Roles> roles;

  public static Author convertEntityToModel(UserEntity userEntity) {
    Author author = new Author();
    author.setId(userEntity.getId());
    author.setUsername(userEntity.getUsername());
    author.setRoles(userEntity.getRoles());
    return author;
  }
}
