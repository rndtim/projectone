package com.projectone.model;

import com.projectone.entities.PostEntity;
import lombok.Data;

import javax.persistence.Column;

@Data
public class Post {
  private Long id;

  private String title;

  private String description;

  public static Post toModel(PostEntity postEntity) {
    Post post = new Post();
    post.setId(postEntity.getId());
    post.setTitle(postEntity.getTitle());
    post.setDescription(postEntity.getDescription());
    return post;
  }
}
