package com.minnela.issue.repository;

import com.minnela.issue.domain.Users;

import java.util.List;

public interface UserRepository  {
    void addUser(Users user);
    List<Users> getUsers();
    List<String> getUserNames();
    Users getUserByUserName(String username);
    Users getUserById(long id);
    String getRoleByUserId(long id);

}
