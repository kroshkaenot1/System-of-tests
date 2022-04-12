package com.rgr.system_of_tests.controllers;

import com.rgr.system_of_tests.repo.UsersRepository;
import com.rgr.system_of_tests.repo.models.Role;
import com.rgr.system_of_tests.repo.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;
import java.util.stream.Collectors;

@Controller
public class AdminController {
    @Autowired
    UsersRepository usersRepository;

    @GetMapping("/admin")
    public String admin(Model model){
        Iterable<User> users = usersRepository.findAll();
        model.addAttribute("users",users);
        return "admin";}

    @GetMapping("/admin/{id}/edit")
    public String adminEdit(@PathVariable(value="id")long id, Model model){
        Optional<User> user = usersRepository.findById(id);
        ArrayList<User> res = new ArrayList<>();
        user.ifPresent(res::add);
        model.addAttribute("user",res);
        model.addAttribute("roles", Role.values());
        return "admin_edit";}
    @PostMapping("/admin/{id}/edit")
    public String adminUpd(@PathVariable(value="id")long id,
                           @RequestParam Map<String, String> form){

        Set<String> roles = Arrays.stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());

        User user = usersRepository.findId(id);

        user.getRoles().clear();

        for(String key : form.keySet()){
            if(roles.contains(key)){
                user.getRoles().add(Role.valueOf(key));
            }
        }
        usersRepository.save(user);

        return "redirect:/admin";
    }
    @PostMapping("/admin/{id}/remove")
    public String adminDelete(@PathVariable(value="id")long id){

        User user = usersRepository.findId(id);

        usersRepository.delete(user);

        return "redirect:/admin";
    }

}