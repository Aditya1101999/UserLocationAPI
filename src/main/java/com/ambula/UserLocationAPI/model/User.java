package com.ambula.UserLocationAPI.model;

import jakarta.persistence.*;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String username;
    private String role;
    private String password;

    public User() {
    }

    public User(String username, String role) {
        this.username = username;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    // Method to create a user
    public static User createUser(String username, String role) {
        User user = new User(username, role);
        // Perform any other necessary operations to save the user
        return user;
    }

    // Method to update a user
    public void updateUser(User newUser) {
        this.username = newUser.getUsername();
        this.role = newUser.getRole();
        // Perform any other necessary operations to update the user
    }

    // Method to delete a user
    public static void deleteUser(Long id) {
        // Perform any necessary operations to delete the user with the given id
    }

    public void setPassword(String testpassword) {
        this.password=testpassword;
    }

    public String getPassword() {
        return password;
    }
}

