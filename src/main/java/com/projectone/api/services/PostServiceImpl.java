package com.projectone.api.services;

import com.projectone.api.dto.Post;
import com.projectone.api.exceptions.NotAllowed;
import com.projectone.store.entities.Image;
import com.projectone.store.entities.PostEntity;
import com.projectone.store.entities.UserEntity;
import com.projectone.store.repositories.ImageRepository;
import com.projectone.store.repositories.PostRepository;
import com.projectone.store.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PostServiceImpl implements PostService {
  private final PostRepository postRepository;
  private final UserRepository userRepository;
  private final ImageRepository imageRepository;

  @Override
  public List<Post> getAllPosts() {
    return postRepository.findAll().stream().map(Post::convertEntityToDTO).collect(Collectors.toList());
  }

  @Override
  public Post getPostById(Long postId) {
    return Post.convertEntityToDTO(postRepository.findPostById(postId));
  }

  @Override
  public Post save(PostEntity postEntity, MultipartFile image) throws IOException {
    String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    UserEntity userEntity = userRepository.findByUsername(username);
    postEntity.setAuthor(userEntity);
    String path = saveImage(image, username);
    Image postImage = imageToModel(image, path, postEntity);
    postEntity.setPostImage(postImage);
    imageRepository.saveAndFlush(postImage);
    return Post.convertEntityToDTO(postRepository.save(postEntity));
  }

  private Image imageToModel(MultipartFile image, String path, PostEntity postEntity) {
    Image model = new Image();
    model.setName(image.getName());
    model.setSize(image.getSize());
    model.setOriginalFileName(image.getOriginalFilename());
    model.setContentType(image.getContentType());
    model.setPath(path);
    model.setPost(postEntity);
    return model;
  }

  private String saveImage(MultipartFile image, String username) throws IOException {
    String folder = "IdeaProjects\\projectone\\front\\src\\assets\\post_images\\" + username;
    File file = new File(folder);
    if (!file.isDirectory())
      Files.createDirectory(Paths.get(folder));

    byte[] bytes = image.getBytes();
    Path path = Paths.get(folder + "/" + image.getOriginalFilename());

    Files.write(path, bytes);
    return image.getOriginalFilename();
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
    return Post.convertEntityToDTO(postRepository.save(newPost));
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
