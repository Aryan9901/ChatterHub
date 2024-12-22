package com.aryan.app.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
// @Table(name="users") if you want to change table name
public class User {
    @Id
    private int id;
    // @Column(name="fName") if you want to change name
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
