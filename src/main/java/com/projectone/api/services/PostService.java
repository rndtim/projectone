package com.projectone.api.services;

import com.projectone.api.dto.Post;
import com.projectone.store.entities.PostEntity;

import java.util.List;

public interface PostService {
  List<Post> getAllPosts();
  Post save(PostEntity postEntity);
  Post getPostById(Long postId);
  Post updatePost(Long postId, PostEntity postEntity);
  void deletePostById(Long postId);
}
