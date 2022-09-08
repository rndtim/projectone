package com.projectone.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JWTResponse {
  private String token;
  private Long id;
  private String username;
  private String email;
}
