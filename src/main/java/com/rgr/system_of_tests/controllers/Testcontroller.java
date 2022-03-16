package com.rgr.system_of_tests.controllers;

import com.rgr.system_of_tests.models.Tests;
import com.rgr.system_of_tests.repo.TestsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Testcontroller {
    @Autowired
    private TestsRepository testsRepository;
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
    @GetMapping("/registration")
    public String reg(Model model){
        return "registration";
    }
}
