package com.rgr.system_of_tests.controllers;

import com.rgr.system_of_tests.repo.models.Roles;
import com.rgr.system_of_tests.repo.models.Users;
import com.rgr.system_of_tests.repo.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.management.relation.Role;
import java.util.*;
import java.util.stream.Collectors;

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
    public String adminUpd(@PathVariable(value="id")long id,
                           @RequestParam Map<String, String> form){

        Set<String> roles = Arrays.stream(Roles.values())
                .map(Roles::name)
                .collect(Collectors.toSet());

        Users user = usersRepository.findId(id);

        user.getRoles().clear();

        for(String key : form.keySet()){
            if(roles.contains(key)){
                user.getRoles().add(Roles.valueOf(key));
            }
        }
        usersRepository.save(user);

        return "redirect:/admin";
    }

}