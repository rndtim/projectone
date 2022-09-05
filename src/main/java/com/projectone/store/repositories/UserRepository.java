package com.projectone.store.repositories;

import com.projectone.store.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
  UserEntity findByUsername(String name);
  UserEntity findByEmail(String email);
}
