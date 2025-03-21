package com.projectone.api.dto;

import com.projectone.store.entities.PostEntity;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class Post {
    private UUID id;
    private String title;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Author author;
    private String image;

    // This method can be realized in Factory(@Component) directory
    // as a public method (entity) with Builder return DTO
    public static Post convertEntityToDTO(PostEntity postEntity) {
        return Post.builder()
                .id(postEntity.getId())
                .title(postEntity.getTitle())
                .description(postEntity.getDescription())
                .createdAt(postEntity.getCreatedAt())
                .updatedAt(postEntity.getUpdatedAt())
                .author(Author.convertEntityToModel(postEntity.getAuthor()))
                .image(postEntity.getPostImage().getOriginalFileName())
                .build();
    }
}
