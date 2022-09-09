package com.projectone.api.dto;

import com.projectone.store.entities.PostEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {
  private Long id;
  private String title;
  private String description;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
  private Author author;

  // This method can be realized in Factory(@Component) directory
  // as a public method (entity) with Builder return DTO
  public static Post convertEntityToModel(PostEntity postEntity) {
    Post post = new Post();
    post.setId(postEntity.getId());
    post.setTitle(postEntity.getTitle());
    post.setDescription(postEntity.getDescription());
    post.setCreatedAt(postEntity.getCreatedAt());
    post.setUpdatedAt(postEntity.getUpdatedAt());
    post.setAuthor(Author.convertEntityToModel(postEntity.getAuthor()));
    return post;
  }
}
