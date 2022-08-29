package com.projectone.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "posts")
public class PostEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(length=50, nullable=false)
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