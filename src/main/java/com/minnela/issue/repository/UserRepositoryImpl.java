package com.minnela.issue.repository;

import com.minnela.issue.domain.Users;
import com.minnela.issue.utils.EncryptedPasswordUtils;
import mapper.UserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.*;

@Repository
public class UserRepositoryImpl implements UserRepository{
    private JdbcTemplate jdbc;
    private EncryptedPasswordUtils encryptedPasswordUtils;

    @Autowired
    public UserRepositoryImpl(DataSource dataSource) {
        this.jdbc = new JdbcTemplate(dataSource);
    }

    @Override
    public void addUser(Users user) {

        user.setUserRole("user");
        String encryptedPassword= encryptedPasswordUtils.encrytePassword(user.getPassword());

        user.setPassword(encryptedPassword);

        SimpleJdbcInsert insertUser = new SimpleJdbcInsert(jdbc).withSchemaName("public").withTableName("users").usingGeneratedKeyColumns("userid");
        Map<String, Object> parameters = new HashMap<>(2);
        parameters.put("userid", user.getId());
        parameters.put("username", user.getName());
        parameters.put("usersurname", user.getSurname());
        parameters.put("userpassword", user.getPassword());
        parameters.put("useremail", user.getUsername());
        parameters.put("userrolee", user.getUserRole());
        Number id = insertUser.executeAndReturnKey(parameters);
        user.setId(id.longValue());
        insertUser.execute(parameters);

    }

    @Override
    public List<Users> getUsers() {
        return jdbc.query("select * from users",new UserRowMapper());
    }

    @Override
    public List<String> getUserNames() {
        List <String> usernames= new ArrayList<String>();
        Iterator iterator = getUsers().iterator();

        while (iterator.hasNext()){
            Users user = (Users) iterator.next();
            usernames.add(user.getUsername());
        }
        return usernames;
    }

    @Override
    public Users getUserByUserName(String username) {
        return jdbc.queryForObject("SELECT * FROM users WHERE useremail = ?", new UserRowMapper(), username);
    }


    @Override
    public Users getUserById(long id) {
        return jdbc.queryForObject("SELECT * FROM users WHERE userid = ?", new UserRowMapper(), id);
    }


    @Override
    public String getRoleByUserId(long id) {
        Users user = getUserById(id);
        return user.getUserRole();
    }

    public static synchronized long createID()
    {
        Random r = new Random();
        long x = 0L;
        long y = 23456789L;
        long number = x+((long)(r.nextDouble()*(y-x)));
        return number;
    }
}
