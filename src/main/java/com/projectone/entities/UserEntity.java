package com.projectone.entities;

import com.projectone.dto.Roles;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
//@Table(name = "users")
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

//  @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
//  @Enumerated(EnumType.STRING)
//  private Set<Roles> roles;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "userEntity")
  private List<PostEntity> userPosts = new ArrayList<>();
}