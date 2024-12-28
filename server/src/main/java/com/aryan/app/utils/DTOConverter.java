package com.aryan.app.utils;

import java.util.stream.Collectors;
import com.aryan.app.dtos.PostDTO;
import com.aryan.app.dtos.UserDTO;
import com.aryan.app.models.entity.Post;
import com.aryan.app.models.entity.User;

public class DTOConverter {

    public static PostDTO toPostDTO(Post post) {
        return new PostDTO(
                post.getPostId(),
                post.getCaption(),
                post.getImage(),
                post.getVideo(),
                post.getUser().getId(),
                post.getCreatedAt());
    }

    public static UserDTO toUserDTO(User user) {
        return new UserDTO(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getSavedPost().stream().map(Post::getPostId).collect(Collectors.toList()), // Only Post IDs
                user.getGender(),
                user.getEmail());
    }
}
