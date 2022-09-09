package com.projectone.api.services;

import com.projectone.api.dto.Post;
import com.projectone.api.exceptions.NotAllowed;
import com.projectone.store.entities.PostEntity;
import com.projectone.store.entities.UserEntity;
import com.projectone.store.repositories.PostRepository;
import com.projectone.store.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PostServiceImpl implements PostService {
  private final PostRepository postRepository;
  private final UserRepository userRepository;

  @Override
  public List<Post> getAllPosts() {
    return postRepository.findAll().stream().map(Post::convertEntityToModel).collect(Collectors.toList());
  }

  @Override
  public Post getPostById(Long postId) {
    return Post.convertEntityToModel(postRepository.findPostById(postId));
  }

  @Override
  public Post save(PostEntity postEntity) {
    String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    UserEntity userEntity = userRepository.findByUsername(username);
    postEntity.setAuthor(userEntity);
    return Post.convertEntityToModel(postRepository.save(postEntity));
  }

  @Override
  public Post updatePost(Long postId, PostEntity postEntity) {
    String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    UserEntity userEntity = userRepository.findByUsername(username);
    if (!Objects.equals(postEntity.getAuthor().getId(), userEntity.getId())) {
      throw new NotAllowed("Not allowed to change the post");
    }
    PostEntity newPost = postRepository.findPostById(postId);
    newPost.setTitle(postEntity.getTitle());
    newPost.setDescription(postEntity.getDescription());
    newPost.setUpdatedAt(LocalDateTime.now());
    return Post.convertEntityToModel(postRepository.save(newPost));
  }

  @Override
  public void deletePostById(Long postId) {
    String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    UserEntity userEntity = userRepository.findByUsername(username);
    PostEntity postEntity = postRepository.findPostById(postId);
    if (!Objects.equals(postEntity.getAuthor().getId(), userEntity.getId())) {
      throw new NotAllowed("Not allowed to delete the post");
    }
    postRepository.deleteById(postId);
  }
}
