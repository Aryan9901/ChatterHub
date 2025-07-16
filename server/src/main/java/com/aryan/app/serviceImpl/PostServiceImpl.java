package com.aryan.app.serviceImpl;

import java.time.LocalDateTime;
import java.util.List;



import org.springframework.stereotype.Service;
import com.aryan.app.models.entity.Post;
import com.aryan.app.models.entity.User;
import com.aryan.app.repository.PostRepository;
import com.aryan.app.repository.UserRepository;
import com.aryan.app.service.PostService;
import com.aryan.app.service.UserService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PostServiceImpl implements PostService {

    PostRepository postRepository;
    UserRepository userRepository;
    UserService userService;

    @Override
    public Post createNewPost(Post post, Integer userId) throws Exception {
        Post newPost = new Post();
        newPost.setCaption(post.getCaption());
        newPost.setImage(post.getImage());
        newPost.setCreatedAt(LocalDateTime.now());
        newPost.setVideo(post.getVideo());

        User user = userService.findUserById(userId);
        newPost.setUser(user);

        Post createdPost = postRepository.save(newPost);
        return createdPost;
    }

    @Override
    public String deletePost(Integer postId, Integer userId) throws Exception {
        Post post = findPostById(postId);

        User user = userService.findUserById(userId);

        if (post.getUser().getId() != user.getId()) {
            throw new Exception("you can't delete anather user's post");
        }

        for (User u : userRepository.findAll()) {
            if (u.getSavedPost().contains(post)) {
                u.getSavedPost().remove(post);
                userRepository.save(u);
            }
        }

        postRepository.delete(post);
        return "Post deleted Successfully";
    }

    @Override
    public List<Post> findPostByUserId(Integer userId) {
        return postRepository.findPostByUserId(userId);
    }

    @Override
    public Post findPostById(Integer postId) throws Exception {
        var post = postRepository.findById(postId);
        if (post.isEmpty()) {
            throw new Exception("Post Not Present with id " + postId);
        }
        return post.get();
    }

    @Override
    public List<Post> findAllPost() {
        return postRepository.findAll();
    }

    @Override
    public Post savedPost(Integer postId, Integer userId) throws Exception {
        Post post = findPostById(postId);
        User user = userService.findUserById(userId);

        if (user.getSavedPost().contains(post)) {
            user.getSavedPost().remove(post);
        } else {
            user.getSavedPost().add(post);
        }
        userRepository.save(user);
        return post;
    }

    @Override
    public Post likePost(Integer postId, Integer userId) throws Exception {
        Post post = findPostById(postId);
        User user = userService.findUserById(userId);

        if (post.getLiked().contains(user)) {
            post.getLiked().remove(user);
        } else {
            post.getLiked().add(user);
        }

        return postRepository.save(post);
    }

}
