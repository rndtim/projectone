package com.projectone.api.services;

import com.projectone.api.dto.Post;
import com.projectone.store.entities.PostEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface PostService {
  List<Post> getAllPosts();
  Post save(PostEntity postEntity, MultipartFile image) throws IOException;
  Post getPostById(Long postId);
  Post updatePost(Long postId, PostEntity postEntity);
  void deletePostById(Long postId);
}
