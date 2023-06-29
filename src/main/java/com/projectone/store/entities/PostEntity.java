package com.projectone.store.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "posts")
public class PostEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(nullable=false)
  private String title;

  @Column(nullable = false, columnDefinition = "text")
  private String description;

  @OneToOne(cascade = CascadeType.ALL, mappedBy = "post")
  private Image postImage;

  @Column(name = "created_at")
  private LocalDateTime createdAt;
  @Column(name = "updated_at")
  private LocalDateTime updatedAt;

  @ManyToOne(cascade = CascadeType.REFRESH)
  @JoinColumn(name = "user_id")
  private UserEntity author;

  @PrePersist
  private void init() {
    createdAt = LocalDateTime.now();
    updatedAt = LocalDateTime.now();
  }

}