package com.projectone.api.dto;

import com.projectone.store.entities.Roles;
import lombok.Data;

import java.util.List;

@Data
public class JWTResponse {
    private String token;
    private Long id;
    private String username;
    private List<Roles> roles;
}
