package com.projectone.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class UserEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long userId;

  @Column(length=50, nullable=false)
  private String name;

  @Column(length=50, nullable=false)
  private String email;

  @Column(length=50, nullable=false)
  private String password;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "userEntity")
  private List<PostEntity> userPosts = new ArrayList<>();
}