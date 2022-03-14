package com.rgr.system_of_tests.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Testcontroller {
    @GetMapping("/test")
    public String testMain(Model model){
        return "test-main";
    }
}
