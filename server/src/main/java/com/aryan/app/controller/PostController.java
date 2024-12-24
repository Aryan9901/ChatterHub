package com.aryan.app.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.aryan.app.models.entity.Post;
import com.aryan.app.response.ApiResponse;
import com.aryan.app.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@AllArgsConstructor
public class PostController {
    PostService postService;

    @PostMapping("/posts/create/{userId}")
    public ResponseEntity<Post> createPostHandler(@RequestBody Post post, @PathVariable Integer userId)
            throws Exception {
        Post createdPost = postService.createNewPost(post, userId);
        return new ResponseEntity<>(createdPost, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/posts/{postId}/{userId}")
    public ResponseEntity<ApiResponse> deletePostHandler(@PathVariable Integer postId, @PathVariable Integer userId)
            throws Exception {
        String message = postService.deletePost(postId, userId);
        ApiResponse response = new ApiResponse(message, true);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/posts/{postId}")
    public ResponseEntity<Post> findPostByIdHandler(@PathVariable Integer postId) throws Exception {
        Post post = postService.findPostById(postId);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @GetMapping("/posts/user/{userId}")
    public ResponseEntity<List<Post>> findUsersPostsHandler(@PathVariable Integer userId) {
        List<Post> posts = postService.findPostByUserId(userId);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @GetMapping("/posts")
    public ResponseEntity<List<Post>> findAllPostsHandler() {
        List<Post> posts = postService.findAllPost();
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @PutMapping("posts/{postId}/user/{userId}")
    public ResponseEntity<Post> savePostHandler(@PathVariable Integer postId, @PathVariable Integer userId)
            throws Exception {
        Post post = postService.savedPost(postId, userId);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @PutMapping("posts/like/{postId}/user/{userId}")
    public ResponseEntity<Post> likePostHandler(@PathVariable Integer postId, @PathVariable Integer userId)
            throws Exception {
        Post post = postService.likePost(postId, userId);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }
}
