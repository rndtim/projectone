package com.projectone.repositories;

import com.projectone.entities.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<PostEntity, Long> {
  PostEntity findPostById(Long postId);
}
