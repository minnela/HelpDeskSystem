package com.minnela.issue.repository;

import com.minnela.issue.domain.Issue;
import mapper.IssueRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Repository
public class IssueRepositoryImpl implements IssueRepository{

    private JdbcTemplate jdbc;
    private String currentLoginedUser;


    @Autowired
    public IssueRepositoryImpl(DataSource dataSource) throws SQLException {

        this.jdbc = new JdbcTemplate(dataSource);

    }

    public IssueRepositoryImpl(String currentLoginedUser) {
        this.currentLoginedUser = currentLoginedUser;
    }

    @Override
    public void addIssue(Issue issue) {

        SimpleJdbcInsert insertIssue = new SimpleJdbcInsert(jdbc).withSchemaName("public").withTableName("issue").usingGeneratedKeyColumns("issueid");
        Map<String, Object> parameters = new HashMap<>(2);
        parameters.put("issueid", issue.getId());
        parameters.put("issueurgency", issue.getUrgency());
        parameters.put("issuedefinition", issue.getDefinition());
        parameters.put("issueuseremail", getCurrentLoginedUser());
        parameters.put("issuetype", issue.getType());
        Number id = insertIssue.executeAndReturnKey(parameters);
        issue.setId(id.longValue());
        insertIssue.execute(parameters);
    }

    @Override
    public void addIssueComment(Issue issue) {
        jdbc.update("update issue set issuecomment = ? where issueid =? ", issue.getIssuecomment(),  issue.getId());
    }

    @Override
    public List<Issue> getUserIssues() {
        return jdbc.query("select * from issue where issueuseremail = ?", new IssueRowMapper(),getCurrentLoginedUser());
    }

    @Override
    public List<Issue> getIssues() {
        return jdbc.query("select * from issue", new IssueRowMapper());
    }

    @Override
    public void deleteIssueById(long id) {
      jdbc.update("DELETE FROM issue WHERE issueid = ?", id);
    }

    @Override
    public Issue getIssueById(long id) {
        return jdbc.queryForObject("SELECT * FROM issue WHERE issueid = ?", new IssueRowMapper(), id);
    }


    public static synchronized long createID()
    {
        Random r = new Random();
        long x = 0L;
        long y = 23456789L;
        long number = x+((long)(r.nextDouble()*(y-x)));
        return number;
    }


    public String getCurrentLoginedUser() {
        return currentLoginedUser;
    }

    public void setCurrentLoginedUser(String currentLoginedUser) {
        this.currentLoginedUser = currentLoginedUser;
    }
}
