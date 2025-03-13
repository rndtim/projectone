package com.projectone.store.repositories;

import com.projectone.store.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {
  UserEntity findByUsername(String name);
  UserEntity findByEmail(String email);
}
