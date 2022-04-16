package com.rgr.system_of_tests.controllers;

import com.rgr.system_of_tests.repo.models.Role;
import com.rgr.system_of_tests.repo.models.User;
import com.rgr.system_of_tests.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@Controller
public class AdminController {
    @Autowired
    private UserService usersService;

    @GetMapping("/admin")
    public String admin(Model model){

        Iterable<User> users = usersService.getAllUsers();
        model.addAttribute("users",users);
        return "admin";}

    @GetMapping("/admin/{id}/edit")
    public String adminEdit(@PathVariable(value="id")long id, Model model){
        User user = usersService.getUserById(id);

        model.addAttribute("user",user);
        model.addAttribute("roles", Role.values());
        return "admin_edit";}

    @PostMapping("/admin/{id}/edit")
    public String adminUpd(@PathVariable(value="id")long id, @RequestParam Map<String, String> form){

        usersService.editUserRoles(form,id);
        return "redirect:/admin";
    }
    @PostMapping("/admin/{id}/remove")
    public String adminDelete(@PathVariable(value="id")long id){

        usersService.deleteUser(id);
        return "redirect:/admin";
    }

}