package com.aryan.app.dtos;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostDTO {
    private int postId;
    private String caption;
    private String image;
    private String video;
    private int userId; // Only include user ID, not the entire User object
    private LocalDateTime createdAt;
}
