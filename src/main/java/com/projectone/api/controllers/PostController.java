package com.projectone.api.controllers;

import com.projectone.api.dto.Post;
import com.projectone.api.services.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/posts")
@AllArgsConstructor
public class PostController {

    private final PostService postService;

    @Tag(name = "Posts Controller", description = "Get posts")
    @Operation(summary = "Get all posts of all users")
    @GetMapping()
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    @Tag(name = "Posts Controller", description = "Get post")
    @Operation(summary = "Get one post from user")
    @GetMapping("/{postId}")
    public Post getOnePostById(@PathVariable UUID postId) {
        return postService.getPostById(postId);
    }

    @Tag(name = "Posts Controller", description = "Create post")
    @Operation(summary = "Create post with image")
    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public Post createPost(@RequestPart("post") Post post, @RequestPart("image") MultipartFile image) throws IOException {
        return postService.save(post, image);
    }

    @Tag(name = "Posts Controller", description = "Update post")
    @Operation(summary = "Update fields of post")
    @PutMapping("/{postId}")
    public Post updatePostById(@PathVariable UUID postId, @RequestBody Post post) {
        return postService.updatePost(postId, post);
    }

    @Tag(name = "Posts Controller", description = "Delete post")
    @Operation(summary = "Delete post by id")
    @DeleteMapping("/{postId}")
    public void deletePostById(@PathVariable UUID postId) {
        postService.deletePostById(postId);
    }
}
