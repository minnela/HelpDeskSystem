package com.minnela.issue.service;

import com.minnela.issue.domain.Users;

import java.util.List;

public interface UserService {
    void addUser(Users user);
    List<Users> getUsers();
    List<String> getUserNames();
    Users getUserByUserName(String username);
    Users getUserById(long id);


}
