package com.nhnacademy.board.service;

import com.nhnacademy.board.entity.User;

import java.util.Optional;

public interface UserService {
     Optional<User> getUser(String username, String password);
     Optional<User> findByUsername(String username);
}
