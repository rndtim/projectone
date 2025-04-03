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
import java.util.UUID;
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
    public Post getPostById(UUID postId) {
        return Post.convertEntityToDTO(postRepository.findPostById(postId));
    }

    @Override
    public Post save(Post post, MultipartFile image) throws IOException {
        PostEntity postEntity = PostEntity.builder()
                .title(post.getTitle())
                .description(post.getDescription())
                .build();
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        UserEntity userEntityAsAuthor = userRepository.findByUsername(username);
        postEntity.setAuthor(userEntityAsAuthor);
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
        String folder = Paths.get(".", "front", "src", "assets", "post_images", username).toString();
        File file = new File(folder);
        if (!file.isDirectory())
            Files.createDirectories(Paths.get(folder));

        byte[] bytes = image.getBytes();
        Path path = Paths.get(folder, image.getOriginalFilename());

        Files.write(path, bytes);
        return path.toString();
    }

    @Override
    public Post updatePost(UUID postId, Post post) {
        PostEntity postEntityToEdit = postRepository.findPostById(postId);
        String username = postEntityToEdit.getAuthor().getUsername();
        UserEntity userEntity = userRepository.findByUsername(username);

        if (!Objects.equals(postEntityToEdit.getAuthor().getId(), userEntity.getId())) {
            throw new NotAllowed("Not allowed to change the post");
        }

        postEntityToEdit.setTitle(post.getTitle());
        postEntityToEdit.setDescription(post.getDescription());
        return Post.convertEntityToDTO(postRepository.save(postEntityToEdit));
    }

    @Override
    public void deletePostById(UUID postId) {
        PostEntity postEntity = postRepository.findPostById(postId);
        String username = postEntity.getAuthor().getUsername();
        UserEntity userEntity = userRepository.findByUsername(username);
        if (!Objects.equals(postEntity.getAuthor().getId(), userEntity.getId())) {
            throw new NotAllowed("Not allowed to delete the post");
        }
        postRepository.deleteById(postId);
    }
}
