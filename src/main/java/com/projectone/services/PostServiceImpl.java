package com.projectone.services;

import com.projectone.dto.Post;
import com.projectone.entities.PostEntity;
import com.projectone.entities.UserEntity;
import com.projectone.repositories.PostRepository;
import com.projectone.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

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

  @Override
  public Post save(Long userId, PostEntity postEntity) {
    UserEntity userEntity = userRepository.getReferenceById(userId);
    postEntity.setUserEntity(userEntity);
    return Post.convertEntityToModel(postRepository.save(postEntity));
  }

  @Override
  public Post getPostByUserId(Long userId, Long postId) {
    UserEntity userEntity = userRepository.getReferenceById(userId);
    return userEntity.getUserPosts().stream()
            .filter(post -> postId.equals(post.getId()))
            .findAny()
            .map(Post::convertEntityToModel).get();
//    return Post.convertEntityToModel(postRepository.findPostById(postId));
  }

  @Override
  public Post getPostById(Long postId) {
    return Post.convertEntityToModel(postRepository.findPostById(postId));
  }

  @Override
  public Post updatePost(Long postId) {
    PostEntity postEntity = postRepository.findPostById(postId);
    return Post.convertEntityToModel(postEntity);
  }

  @Override
  public void deletePostById(Long postId) {

  }
}
