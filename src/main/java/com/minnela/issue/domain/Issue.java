package com.minnela.issue.domain;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
public class Issue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="issueid",nullable = false, updatable = false)
    private long id;

    @Column(name="issuetype", updatable = false)
    private String type;

    @Column(name="issueurgency", updatable = false)
    private String urgency;

    @Column(name="issuedefinition", updatable = false)
    private String definition;

    @Column(name="issueuseremail", updatable = false)
    private String userEmail;

    @Column(name="issuecomment", updatable = false)
    private String issuecomment;

    @Size(min=3, max=50)
    private String UserName;

    @Size(min=3, max=50)
    private String UserSurname;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Users user;

    public Issue(){

    }

    public Issue(long id, String type, String urgency, String definition, String userEmail) {
        this.id=id;
        this.type = type;
        this.urgency = urgency;
        this.definition = definition;
        this.userEmail = userEmail;
    }

    @Override
    public String toString() {
        return "Issue{" +
                "id=" + id +
                ", urgency='" + urgency + '\'' +
                ", type='" + type + '\'' +
                ", definition='" + definition+
                '}';
    }

    public String getUrgency() {
        return urgency;
    }

    public void setUrgency(String urgency) {
        this.urgency = urgency;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getUserSurname() {
        return UserSurname;
    }

    public void setUserSurname(String userSurname) {
        UserSurname = userSurname;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userNickName) {
        this.userEmail = userNickName;
    }

    public String getIssuecomment() {
        return issuecomment;
    }

    public void setIssuecomment(String issuecomment) {
        this.issuecomment = issuecomment;
    }
}
