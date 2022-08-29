package com.projectone.api.services;

import com.projectone.api.dto.Post;
import com.projectone.store.entities.PostEntity;

import java.security.Principal;
import java.util.List;

public interface PostService {
  List<Post> getAllPostByUser(Long userId);
  Post save(Principal principal, PostEntity postEntity);
  Post getPostByUserId(Long userId, Long postId);
  Post getPostById(Long postId);
  Post updatePost(Long postId, PostEntity postEntity);
  void deletePostById(Long postId);
}
