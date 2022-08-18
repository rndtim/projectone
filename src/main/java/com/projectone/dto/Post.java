package com.projectone.dto;

import com.projectone.entities.PostEntity;
import lombok.Data;

@Data
public class Post {
  private Long id;
  private String title;
  private String description;

  public static Post convertEntityToModel(PostEntity postEntity) {
    Post post = new Post();
    post.setId(postEntity.getId());
    post.setTitle(postEntity.getTitle());
    post.setDescription(postEntity.getDescription());
    return post;
  }
}
