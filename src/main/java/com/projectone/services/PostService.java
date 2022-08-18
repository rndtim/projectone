package com.projectone.services;

import com.projectone.dto.Post;
import com.projectone.entities.PostEntity;

import java.util.List;

public interface PostService {
  List<Post> getAllPostByUser(Long userId);
  Post save(PostEntity postEntity, Long userId);
  Post getPostById(Long postId);
  Post updatePost(Long postId);
  void deletePostById(Long postId);
}
