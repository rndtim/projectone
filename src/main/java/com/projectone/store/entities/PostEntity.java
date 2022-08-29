package com.projectone.store.entities;

import lombok.*;

import javax.persistence.*;
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

  @Column(nullable = false)
  private String description;

  private LocalDateTime createdAt;

  @ManyToOne(cascade = CascadeType.REFRESH)
  @JoinColumn(name = "user_id")
  private UserEntity author;

  @PrePersist
  private void init() {
    createdAt = LocalDateTime.now();
  }
}