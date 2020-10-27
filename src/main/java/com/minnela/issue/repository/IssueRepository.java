package com.minnela.issue.repository;

import com.minnela.issue.domain.Issue;

import java.util.List;

public interface IssueRepository {
    void addIssue(Issue issue);
    List<Issue> getUserIssues();
    void deleteIssueById(long id);
    Issue getIssueById(long id);
    List<Issue> getIssues();
    void addIssueComment(Issue issue);
}
