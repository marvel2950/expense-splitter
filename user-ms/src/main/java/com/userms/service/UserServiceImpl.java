package com.userms.service;

import com.userms.exception.UserNotFoundException;
import com.userms.model.User;
import com.userms.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User addUser(User user) {
        if (userRepository != null) {
            return userRepository.save(user);
        }
        return null;
    }

    @Override
    public List<User> addUsers(List<User> users) {
        if (userRepository != null) {
            return userRepository.saveAll(users);
        }
        return new ArrayList<>();
    }

    @Override
    public ResponseEntity<Object> updateUser(int userId, Integer expense) {
        try {
            User updatedUser = updateUserUtil(userId, expense);
            return ResponseEntity.ok(updatedUser);
        } catch (UserNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body("Invalid argument provided");
        }
    }

    private User updateUserUtil(int userId, Integer expense) throws UserNotFoundException{
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User with ID " + userId + " not found"));
        existingUser.setExpense(expense);
        return userRepository.save(existingUser);
    }

    @Override
    public ResponseEntity<Object> deleteUser(int userId) {
        try {
            deleteUserUtil(userId);
            return ResponseEntity.ok("User deleted successfully");
        } catch (UserNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body("Invalid argument provided");
        }
    }

    public void deleteUserUtil(int userId)  throws UserNotFoundException {
        userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User with ID " + userId + " not found"));
        userRepository.deleteById(userId);
    }

    @Override
    public void deleteAllUsers() {
        userRepository.deleteAll();
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
