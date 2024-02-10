package com.userms.controller;

import com.userms.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

public interface UserController {

    public ResponseEntity<Object> addUser(User user);

    public ResponseEntity<Object> addUsers(@RequestBody List<User> users);

    public ResponseEntity<Object> updateUser(@PathVariable int userId, @RequestBody Integer expense);

    public ResponseEntity<Object> deleteUser(@PathVariable int userId);

    public ResponseEntity<Object> deleteAllUsers();

    public ResponseEntity<Object> getAllUsers();
}
