package com.example.demo.services;

import com.example.demo.entities.User;
import java.util.List;

public interface UserService {
    List<User> findAll();
    User findById(Long id);
    User save(User user);
    void deleteById(Long id);
    User update(Long id, User userDetails);
}