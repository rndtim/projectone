package com.projectone.api.controllers;

import com.projectone.api.dto.Post;
import com.projectone.api.services.PostService;
import com.projectone.store.entities.PostEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PostController {

  private final PostService postService;

  public PostController(PostService postService) {
    this.postService = postService;
  }

  @GetMapping("/posts")
  public List<Post> getAllPosts() {
    return postService.getAllPosts();
  }

  @GetMapping("/posts/{postId}")
  @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
  public Post getOnePostById(@PathVariable Long postId) {
    return postService.getPostById(postId);
  }

  @PostMapping("/posts")
  @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
  public Post createPost(@RequestBody PostEntity postEntity) {
    return postService.save(postEntity);
  }

  @PutMapping("/posts/{postId}")
  @PreAuthorize("hasRole('USER')")
  public Post updatePostById(@PathVariable Long postId, @RequestBody PostEntity postEntity) {
    return postService.updatePost(postId, postEntity);
  }

  @DeleteMapping("/posts/{postId}")
  @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
  public void deletePostById(@PathVariable Long postId) {
    postService.deletePostById(postId);
  }
}
