package com.minnela.issue.controller;

import com.minnela.issue.service.IssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class LoginController {
    private IssueService issueService;

    @Autowired
    public LoginController(IssueService issueService) {
        this.issueService = issueService;
    }

    @PreAuthorize("isAnonymous()")
    @RequestMapping(value = "/login")
    public ModelAndView getLoginPage(@RequestParam Optional<String> error) {
        return new ModelAndView("loginPage", "error", error);
    }


    @RequestMapping(value = "/logoutSuccessful", method = RequestMethod.GET)
    public String logoutSuccessfulPage(Model model) {
        model.addAttribute("title", "Logout");
        return "logoutSuccessfulPage";
    }
}
