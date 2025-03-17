package com.projectone.store.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Data
@Entity
@Builder
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;

  @Column(nullable = false, unique = true)
  private String username;

  @Column(nullable = false, unique = true)
  private String email;

//  @Column(length = 1000, nullable = false)
//  private String password;

//  private boolean isActive;

  @ElementCollection(targetClass = Roles.class, fetch = FetchType.EAGER)
  @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
  @Enumerated(EnumType.STRING)
  private List<Roles> roles = new ArrayList<>();

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "author")
  private List<PostEntity> userPosts = new ArrayList<>();
}