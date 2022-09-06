package com.projectone.api.controllers;

import com.projectone.api.dto.Post;
import com.projectone.api.services.PostService;
import com.projectone.store.entities.PostEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PostController {

  private final PostService postService;

  public PostController(PostService postService ) {
    this.postService = postService;
  }

  @GetMapping("/posts")
  public List<Post> getAllUserPosts(@RequestParam Long userId) {
    return postService.getAllPostByUser(userId);
  }

//  @GetMapping("/{userId}")
//  public Post getOnePostByUserId(@PathVariable Long userId, @RequestParam Long postId) {
//    return postService.getPostByUserId(userId, postId);
//  }

  @GetMapping("/posts/{postId}")
  public Post getOnePostById(@PathVariable Long postId) {
    return postService.getPostById(postId);
  }

  @PostMapping("/posts")
  public Post createPost(@RequestBody PostEntity postEntity){
    return postService.save(postEntity);
  }

  @PutMapping("/posts/{postId}")
  public Post updatePostById(@PathVariable Long postId, @RequestBody PostEntity postEntity){
    return postService.updatePost(postId, postEntity);
  }

  @DeleteMapping("/posts/{postId}")
  public void deletePostById(@PathVariable Long postId) {
    postService.deletePostById(postId);
  }
}
