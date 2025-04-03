package com.projectone.api.dto;

import com.projectone.store.entities.Roles;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class JWTResponse {
    private String token;
    private UUID id;
    private String username;
    private List<Roles> roles;
}
