package com.userms.service;

import com.userms.model.User;
import org.springframework.http.ResponseEntity;
import java.util.List;

public interface UserService {
    User addUser(User user);

    List<User> addUsers(List<User> users);

    ResponseEntity<Object> updateUser(int userId, Integer expenseAmount);

    List<User> getAllUsers();

    ResponseEntity<Object> deleteUser(int userId);

    void deleteAllUsers();
}
