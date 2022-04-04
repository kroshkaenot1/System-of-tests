package com.rgr.system_of_tests.controllers;

import com.rgr.system_of_tests.models.Roles;
import com.rgr.system_of_tests.models.Tests;
import com.rgr.system_of_tests.models.Users;
import com.rgr.system_of_tests.repo.TestsRepository;
import com.rgr.system_of_tests.repo.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.List;

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
    @GetMapping("/registration")
    public String reg(Model model){
        return "registration";
    }
    @PostMapping("/registration")
    public String regg(@RequestParam String username,String password, Users user){
        Users userfromDB =usersRepository.findByUsername(user.getUsername());
        if(userfromDB!=null){
            return "login";
        }
        user.setRoles(Collections.singleton(Roles.USER));
        user.setActive(true);
        usersRepository.save(user);
        return "login";
    }
    @GetMapping("/admin")
    public String admin(Model model){
    Iterable<Users> users = usersRepository.findAll();
    model.addAttribute("users",users);
    return "admin";}
}
