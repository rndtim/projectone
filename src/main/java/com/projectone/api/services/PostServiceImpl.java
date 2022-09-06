package com.projectone.api.services;

import com.projectone.api.dto.Post;
import com.projectone.store.entities.PostEntity;
import com.projectone.store.entities.UserEntity;
import com.projectone.store.repositories.PostRepository;
import com.projectone.store.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PostServiceImpl implements PostService {
  private final PostRepository postRepository;
  private final UserRepository userRepository;

  @Override
  public List<Post> getAllPostByUser(Long userId) {
    UserEntity userEntity = userRepository.getReferenceById(userId);
    return userEntity.getUserPosts().stream().map(Post::convertEntityToModel).collect(Collectors.toList());
  }

  private static Post getPostFromUser(Long postId, UserEntity userEntity) {
    return userEntity.getUserPosts().stream()
            .filter(post -> postId.equals(post.getId()))
            .findFirst()
            .map(Post::convertEntityToModel)
            .get();
  }

  @Override
  public Post getPostByUsername(String username, Long postId) {
    UserEntity userEntity = userRepository.findByUsername(username);
    return getPostFromUser(postId, userEntity);
//    return Post.convertEntityToModel(postRepository.findPostById(postId));
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
    PostEntity newPost = postRepository.findPostById(postId);
    newPost.setTitle(postEntity.getTitle());
    newPost.setDescription(postEntity.getDescription());
    newPost.setUpdatedAt(LocalDateTime.now());
    return Post.convertEntityToModel(postRepository.save(newPost));
  }

  @Override
  public void deletePostById(Long postId) {
    postRepository.deleteById(postId);
  }
}
