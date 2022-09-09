package com.projectone.api.dto;

import com.projectone.store.entities.Roles;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
public class JWTResponse {
  private String token;
  private Long id;
  private String username;
  private Set<Roles> roles;
}
