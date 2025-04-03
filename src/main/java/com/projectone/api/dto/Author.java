package com.projectone.api.dto;

import com.projectone.store.entities.UserEntity;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class Author {
    private UUID id;
    private String username;

    public static Author convertEntityToModel(UserEntity userEntity) {
        return Author.builder()
                .id(userEntity.getId())
                .username(userEntity.getUsername())
                .build();
    }
}
