package com.rgr.system_of_tests.controllers;

import com.rgr.system_of_tests.models.Roles;
import com.rgr.system_of_tests.models.Users;
import com.rgr.system_of_tests.repo.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {
    @Autowired
    UsersRepository usersRepository;

    @GetMapping("/admin")
    public String admin(Model model){
        Iterable<Users> users = usersRepository.findAll();
        model.addAttribute("users",users);
        return "admin";}
}
