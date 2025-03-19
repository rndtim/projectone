package com.projectone.api.controllers;

import com.projectone.api.dto.Post;
import com.projectone.api.services.PostService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping()
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    @GetMapping("/{postId}")
    public Post getOnePostById(@PathVariable UUID postId) {
        return postService.getPostById(postId);
    }

    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public Post createPost(@RequestPart("post") Post post, @RequestPart("image") MultipartFile image) throws IOException {
        return postService.save(post, image);
    }

    @PutMapping("/{postId}")
    public Post updatePostById(@PathVariable UUID postId, @RequestBody Post post) {
        return postService.updatePost(postId, post);
    }

    @DeleteMapping("/{postId}")
    public void deletePostById(@PathVariable UUID postId) {
        postService.deletePostById(postId);
    }
}
