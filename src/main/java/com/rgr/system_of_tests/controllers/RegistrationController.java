package com.rgr.system_of_tests.controllers;

import com.rgr.system_of_tests.repo.models.User;
import com.rgr.system_of_tests.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Locale;

@Controller
public class RegistrationController {
    @Autowired
    private UserService usersService;
    @Autowired
    private MessageSource messageSource;

    @GetMapping("/registration")
    public String reg(){
        return "registration";
    }

    @PostMapping("/registration")
    public String regg(User user, Model model){
       if(!usersService.addUser(user)){
           model.addAttribute("message",messageSource.getMessage("registration.fail",new Object[0],new Locale("ru")));
           return "registration";
       }
       model.addAttribute("message",messageSource.getMessage("registration.checkMail",new Object[0],new Locale("ru")));
       return "login";
    }

    @GetMapping("/activate/{code}")
    public String activate(Model model, @PathVariable String code){
        boolean isActived = usersService.activateUser(code);
        if(isActived){
            model.addAttribute("message",messageSource.getMessage("activation.success",new Object[0],new Locale("ru")));
        } else {
            model.addAttribute("message",messageSource.getMessage("activation.failed",new Object[0],new Locale("ru")));
        }
        return "login";
    }
}
