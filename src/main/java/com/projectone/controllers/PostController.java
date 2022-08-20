package com.projectone.controllers;

import com.projectone.dto.Post;
import com.projectone.entities.PostEntity;
import com.projectone.services.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/posts")
public class PostController {

  private final PostService postService;

  public PostController(PostService postService ) {
    this.postService = postService;
  }

  @GetMapping
  public List<Post> getAllUserPosts(@RequestParam Long userId) {
    return postService.getAllPostByUser(userId);
  }

//  @GetMapping("/{userId}")
//  public Post getOnePostByUserId(@PathVariable Long userId, @RequestParam Long postId) {
//    return postService.getPostByUserId(userId, postId);
//  }

  @GetMapping("/{postId}")
  public Post getOnePostById(@PathVariable Long postId) {
    return postService.getPostById(postId);
  }

  @PostMapping
  public Post createPost(@RequestParam Long userId, @RequestBody PostEntity postEntity){
    return postService.save(userId, postEntity);
  }

  @PutMapping("/{postId}")
  public Post updatePostById(@PathVariable Long postId){
    return null;
  }
}
