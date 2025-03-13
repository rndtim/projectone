package com.projectone.api.services;

import com.projectone.api.dto.Post;
import com.projectone.store.entities.PostEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

public interface PostService {
  List<Post> getAllPosts();
  Post save(Post post, MultipartFile image) throws IOException;
  Post getPostById(UUID postId);
  Post updatePost(UUID postId, Post post);
  void deletePostById(UUID postId);
}
