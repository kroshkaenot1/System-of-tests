package com.rgr.system_of_tests.controllers;

import com.rgr.system_of_tests.models.Tests;
import com.rgr.system_of_tests.models.Users;
import com.rgr.system_of_tests.repo.TestsRepository;
import com.rgr.system_of_tests.repo.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class Testcontroller {
    @Autowired
    private TestsRepository testsRepository;

    @Autowired
    private UsersRepository usersRepository;

    @GetMapping("/")
    public String home(Model model){
        return "home";
    }
    @GetMapping("/test")
    public String testMain(Model model){
        Iterable<Tests> tests = testsRepository.findAll();
        model.addAttribute("tests",tests);
        return "test-main";
    }
    @GetMapping("/login")
    public String login(Model model){
        return "login";
    }
    @PostMapping("/login")
    public String log(Model model){
        return "home";
    }
    @GetMapping("/admin")
    public String admin(Model model){
    Iterable<Users> users = usersRepository.findAll();
    model.addAttribute("users",users);
    return "admin";}
}
