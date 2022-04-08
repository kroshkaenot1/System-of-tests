package com.rgr.system_of_tests.controllers;

import com.rgr.system_of_tests.models.Roles;
import com.rgr.system_of_tests.models.Tests;
import com.rgr.system_of_tests.models.Users;
import com.rgr.system_of_tests.repo.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;

@Controller
public class AdminController {
    @Autowired
    UsersRepository usersRepository;

    @GetMapping("/admin")
    public String admin(Model model){
        Iterable<Users> users = usersRepository.findAll();
        model.addAttribute("users",users);
        return "admin";}


    @GetMapping("/admin/{id}/edit")
    public String adminEdit(@PathVariable(value="id")long id, Model model){
        Optional<Users> user = usersRepository.findById(id);
        ArrayList<Users> res = new ArrayList<>();
        user.ifPresent(res::add);
        model.addAttribute("user",res);
        model.addAttribute("roles",Roles.values());
        return "admin-edit";}
    @PostMapping("/admin/{id}/edit")
    public String adminUpd(@PathVariable(value="id")long id){
        return "redirect:/admin";
    }

}