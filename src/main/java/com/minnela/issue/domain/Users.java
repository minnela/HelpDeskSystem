package com.minnela.issue.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="userid", nullable = false,updatable = false)
    private long id;

    @Column(name="userEmail", nullable = false)
    private String username;

    @Column(name="userpassword", nullable = false)
    private String password;

    @Column(name="userName")
    private String name;

    @Column(name="userSurname")
    private String surname;

    @Column(name= "userrole")
    private String userRole;

    @OneToMany(mappedBy = "user")
    private Set<Issue> issues;

    public Users(String username, String password){
        this.username= username;
        this.password=password;
    }

    public Users(){

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Set<Issue> getIssues() {
        return issues;
    }

    public void setIssues(Set<Issue> issues) {
        this.issues = issues;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }
}
