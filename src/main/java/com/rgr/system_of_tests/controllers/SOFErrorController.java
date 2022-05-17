package com.rgr.system_of_tests.controllers;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SOFErrorController implements ErrorController {
    @RequestMapping("/error")
    public String handleError(){
        return "error";
    }
}
