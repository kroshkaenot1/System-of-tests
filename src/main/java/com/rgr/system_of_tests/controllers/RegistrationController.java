package com.rgr.system_of_tests.controllers;

import com.rgr.system_of_tests.repo.models.User;
import com.rgr.system_of_tests.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {
    @Autowired
    private UsersService usersService;

    @GetMapping("/registration")
    public String reg(){
        return "registration";
    }

    @PostMapping("/registration")
    public String regg(User user, Model model){
       if(!usersService.addUser(user)){
           model.addAttribute("message","Пользователь существует!");
           return "registration";
       }
       return "redirect:/login";
    }

    @GetMapping("/activate/{code}")
    public String activate(Model model, @PathVariable String code){
        boolean isActived = usersService.activateUser(code);
        if(isActived){
            model.addAttribute("message","Вы успешно прошли активацию!");
        } else {
            model.addAttribute("message","Код активации не найден!");
        }
        return "login";
    }
}
