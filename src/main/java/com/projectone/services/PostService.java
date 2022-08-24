package com.projectone.services;

import com.projectone.dto.Post;
import com.projectone.entities.PostEntity;

import java.util.List;

public interface PostService {
  List<Post> getAllPostByUser(Long userId);
  Post save(Long userId, PostEntity postEntity);
  Post getPostByUserId(Long userId, Long postId);
  Post getPostById(Long postId);
  Post updatePost(Long postId, PostEntity postEntity);
  void deletePostById(Long postId);
}
