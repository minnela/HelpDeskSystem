package com.minnela.issue.service;

import com.minnela.issue.domain.Issue;

import java.util.List;

public interface IssueService {
    void addIssue(Issue issue);
    List<Issue> getUserIssues();
    void deleteIssueById(long id);
    Issue getIssueById(long id);
    List<Issue> getIssues();
    void addIssueComment(Issue issue);

}
