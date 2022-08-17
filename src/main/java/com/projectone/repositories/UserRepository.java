package com.projectone.repositories;

import com.projectone.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
  UserEntity findUserByEmail(String email);
}
