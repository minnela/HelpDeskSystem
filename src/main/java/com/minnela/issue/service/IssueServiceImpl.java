package com.minnela.issue.service;


import com.minnela.issue.domain.Issue;
import com.minnela.issue.repository.IssueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
public class IssueServiceImpl implements IssueService {

    @Resource
    IssueRepository issueRepository;

    @Autowired
    public IssueServiceImpl(IssueRepository issueRepository) {
        this.issueRepository = issueRepository;
    }

    @Override
    public void addIssue(Issue issue) {
        issueRepository.addIssue(issue);
    }

    @Override
    public List<Issue> getUserIssues() {
        return issueRepository.getUserIssues();
    }

    @Override
    public List<Issue> getIssues() {
        return issueRepository.getIssues();
    }

    @Override
    public void addIssueComment(Issue issue) {
        issueRepository.addIssueComment(issue);
    }

    @Override
    public void deleteIssueById(long id) {
        issueRepository.deleteIssueById(id);
    }

    @Override
    public Issue getIssueById(long id) {
        return issueRepository.getIssueById(id);
    }



}
