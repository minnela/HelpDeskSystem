package com.minnela.issue.controller;


import com.minnela.issue.domain.Issue;
import com.minnela.issue.service.IssueService;
import com.minnela.issue.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.NoSuchElementException;

@Controller
public class IssueController {
    private IssueService issueService;
    private UserService userService;

    @Autowired
    public IssueController(IssueService issueService, UserService userService) {
        this.issueService = issueService;
        this.userService = userService;
    }

    @RequestMapping("/issues/add")
    public ModelAndView issueAddPage(){ return new ModelAndView("addIssue", "issue", new Issue()); }

    @RequestMapping(value="/issues", method= RequestMethod.POST)
    public String handleIssueAdd(@Valid @ModelAttribute("issue") Issue issue, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "addIssue";
        }
        issueService.addIssue(issue);
        return "redirect:/issues";
    }

    @RequestMapping(value = "/issues", method = RequestMethod.GET)
    public ModelAndView getIssuesPage(){

        return new ModelAndView("issues","issues", issueService.getUserIssues());
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public ModelAndView adminPage(){

        return new ModelAndView("adminPage","issues", issueService.getIssues());
    }

  @RequestMapping(value="/issues/{id}", method= RequestMethod.DELETE)
    public String handleItemDelete(@PathVariable long id){
        if (null == issueService.getIssueById(id)){
            throw new NoSuchElementException("Issue with id:" + id + " not found");
        }
        else{
            issueService.deleteIssueById(id);
            return "redirect:/issues";
        }

    }

    @RequestMapping(value="/requestSolutionPage", method = RequestMethod.GET)
    public ModelAndView  getRequestSolutionPage(){
        return new ModelAndView("requestSolutionPage", "issue", new Issue());
    }

    @RequestMapping(value="/showSolution", method= RequestMethod.POST)
    public String handleShowSolutionPage(@Valid @ModelAttribute("issue") Issue issue, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "requestSolutionPage";
        }
        issueService.addIssueComment(issue);
        return "redirect:/showAdminSolution";
    }

    @RequestMapping("/showSolution")
    public ModelAndView  getShowSolutionPage(){
        return new ModelAndView("showSolution","issues", issueService.getUserIssues());
    }

    @RequestMapping("/showAdminSolution")
    public ModelAndView getShowAdminSolutionPage(){
        return new ModelAndView("showAdminSolution","issues", issueService.getIssues());
    }

    @RequestMapping(value="/issues/{id}/get", method= RequestMethod.GET)
    public ModelAndView getIssuePageById(@PathVariable long id) {
        if (null == issueService.getIssueById(id)) {
            throw new NoSuchElementException("Issue with id:" + id + " not found");
        } else {
            return new ModelAndView("issues", "issues", issueService.getIssueById(id));
        }

    }
}
