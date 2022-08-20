package com.projectone.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class PostEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(length=50, nullable=false)
  private String title;

  @Column(nullable = false)
  private String description;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private UserEntity userEntity;
}
