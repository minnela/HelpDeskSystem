package mapper;

import com.minnela.issue.domain.Issue;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class IssueRowMapper implements RowMapper<Issue> {

    @Override
    public Issue mapRow(ResultSet rs, int arg1) throws SQLException {
        Issue issue = new Issue();
        issue.setId(rs.getLong("issueId"));
        issue.setDefinition(rs.getString("issuedefinition"));
        issue.setUrgency(rs.getString("issueurgency"));
        issue.setType(rs.getString("issuetype"));
        issue.setUserEmail(rs.getString("issueuseremail"));
        issue.setIssuecomment(rs.getString("issuecomment"));

        return issue;
    }

}
