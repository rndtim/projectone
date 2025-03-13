package com.projectone.api.dto;

import com.projectone.store.entities.UserEntity;
import lombok.Data;

import java.util.UUID;

@Data
public class Author {
  private UUID id;
  private String username;

  public static Author convertEntityToModel(UserEntity userEntity) {
    Author author = new Author();
    author.setId(userEntity.getId());
    author.setUsername(userEntity.getUsername());
    return author;
  }
}
