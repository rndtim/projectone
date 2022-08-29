package com.projectone.store.repositories;

import com.projectone.store.entities.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<PostEntity, Long> {
  PostEntity findPostById(Long postId);
}
