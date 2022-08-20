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

  @PostMapping
  public Post createPost(@RequestBody PostEntity postEntity, @RequestParam Long userId){
    return postService.save(postEntity, userId);
  }

  @PutMapping("/{postId}")
  public Post updatePostById(@PathVariable Long postId){
    return null;
  }
}
