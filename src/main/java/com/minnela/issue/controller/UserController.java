package com.minnela.issue.controller;

import com.minnela.issue.domain.Users;
import com.minnela.issue.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.sql.SQLException;

@Controller
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/register")
    public ModelAndView getRegisterPage(){
        return new ModelAndView("register", "user", new Users());
    }

    @RequestMapping(value="/register", method= RequestMethod.POST)
    public String handleRegisterForm(@Valid @ModelAttribute("user") Users user, BindingResult bindingResult) throws SQLException {
        if(bindingResult.hasErrors()){
            return "register";
        }
        userService.addUser(user);

        return "redirect:/";
    }

    @RequestMapping("/users")
    public ModelAndView getUsersPage(){
        return new ModelAndView("users","users",userService.getUsers());
    }


}
