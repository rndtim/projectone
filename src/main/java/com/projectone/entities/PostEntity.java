package com.projectone.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
//@Table(name = "posts")
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