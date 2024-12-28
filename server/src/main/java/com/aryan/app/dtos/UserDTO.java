package com.aryan.app.dtos;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private int id;
    private String firstName;
    private String lastName;
    private List<Integer> savedPostIds; // Only include Post IDs, not the full Post objects
    private String gender;
    private String email;
}
