package com.minnela.issue.service;

import com.minnela.issue.domain.Users;
import com.minnela.issue.repository.IssueRepositoryImpl;
import com.minnela.issue.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Resource
    UserRepository userRepository;
    IssueRepositoryImpl issueRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, IssueRepositoryImpl issueRepository) {
        this.userRepository = userRepository;
        this.issueRepository=issueRepository;
    }

    @Override
    public void addUser(Users user) {
     userRepository.addUser(user);
    }

    @Override
    public List<Users> getUsers() {
        return userRepository.getUsers();
    }

    @Override
    public List<String> getUserNames() {
        return userRepository.getUserNames();
    }
    @Override
    public Users getUserByUserName(String username) {
        return userRepository.getUserByUserName(username);
    }

    @Override
    public Users getUserById(long id) {
        return userRepository.getUserById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Users appUser = this.userRepository.getUserByUserName(userName);

       appUser.getPassword();

        if (appUser == null) {
            System.out.println("User not found! " + userName);
            throw new UsernameNotFoundException("User " + userName + " was not found in the database");
        }
        issueRepository.setCurrentLoginedUser(appUser.getUsername());
        System.out.println("Found User: " + appUser);

        // [ROLE_USER, ROLE_ADMIN,..]
        String roleName = this.userRepository.getRoleByUserId(appUser.getId());

        List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
        if (roleName != null) {
                // ROLE_USER, ROLE_ADMIN,..
                GrantedAuthority authority = new SimpleGrantedAuthority(roleName);
                grantList.add(authority);

        }
        UserDetails userDetails = (UserDetails) new User(appUser.getUsername(), //
                appUser.getPassword(), true,true,true,true,grantList);

        return userDetails;
    }


}
