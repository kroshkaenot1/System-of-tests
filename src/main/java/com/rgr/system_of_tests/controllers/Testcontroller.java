package com.rgr.system_of_tests.controllers;

import com.rgr.system_of_tests.models.Tests;
import com.rgr.system_of_tests.models.Users;
import com.rgr.system_of_tests.repo.TestsRepository;
import com.rgr.system_of_tests.repo.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

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
    /*@GetMapping("/test/+{id}+/edit")
    public String testEdit(@PathVariable(value = "id") long id, Model model){
        if(!testsRepository.existsById(id)){
            return "redirect:/test";
        }
        Optional<Tests> test = testsRepository.findById(id);
        model.addAttribute("message",test);
        return "test-edit";
    }*/
    @GetMapping("/admin")
    public String admin(Model model){
    Iterable<Users> users = usersRepository.findAll();
    model.addAttribute("users",users);
    return "admin";}
}
