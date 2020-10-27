package mapper;

import com.minnela.issue.domain.Users;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<Users> {

    @Override
    public Users mapRow(ResultSet rs, int rowNum) throws SQLException {
        Users user = new Users();
        user.setId(rs.getLong("userid"));
        user.setName(rs.getString("username"));
        user.setSurname(rs.getString("usersurname"));
        user.setUsername(rs.getString("useremail"));
        user.setPassword(rs.getString("userpassword"));
        user.setUserRole(rs.getString("userrolee"));
        return user;
    }

}
