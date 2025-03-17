package com.projectone.store.repositories;

import com.projectone.store.entities.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PostRepository extends JpaRepository<PostEntity, UUID> {
  PostEntity findPostById(UUID postId);
}
