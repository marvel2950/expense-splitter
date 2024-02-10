package com.userms.controller;

import com.userms.model.User;
import com.userms.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserControllerImpl implements UserController {

    private final UserService userService;

    public UserControllerImpl(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/hello")
    public String getHello(){
        return "Hello Akarsh!";
    }

    @PostMapping("/user")
    public ResponseEntity<Object> addUser(@RequestBody User user) {
        try {
            User savedUser = this.userService.addUser(user);
            return ResponseEntity.ok(savedUser);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add new user");
        }
    }

    @PostMapping("/users")
    public ResponseEntity<Object> addUsers(@RequestBody List<User> users) {
        try {
            List<User> savedUsers = this.userService.addUsers(users);
            return ResponseEntity.ok(savedUsers);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add new users");
        }
    }

    @PutMapping("/{userId}")
    public ResponseEntity<Object> updateUser(@PathVariable int userId, @RequestBody Integer expense) {
        return this.userService.updateUser(userId, expense);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Object> deleteUser(@PathVariable int userId) {
        return userService.deleteUser(userId);
    }

    @DeleteMapping("/users")
    public ResponseEntity<Object> deleteAllUsers() {
        try {
            userService.deleteAllUsers();
            return ResponseEntity.ok("All users deleted successfully");
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete all users");
        }
    }

    @GetMapping("/users")
    public ResponseEntity<Object> getAllUsers() {
        try {
            List<User> users = this.userService.getAllUsers();
            return ResponseEntity.ok(users);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to fetch all users");
        }
    }

}
