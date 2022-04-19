package com.rgr.system_of_tests.controllers;

import com.rgr.system_of_tests.repo.models.Question;
import com.rgr.system_of_tests.repo.models.QuestionModel;
import com.rgr.system_of_tests.repo.models.Test;
import com.rgr.system_of_tests.repo.models.User;
import com.rgr.system_of_tests.service.QuestionService;
import com.rgr.system_of_tests.service.TestService;
import com.rgr.system_of_tests.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class Testcontroller {
    @Autowired
    private TestService testService;
    @Autowired
    QuestionService questionService;
    @Autowired
    private UserService usersService;

    @GetMapping("/")
    public String home(){
        return "home";
    }

    @GetMapping("/test")
    public String testMain(Model model){
        Iterable<Test> tests = testService.getAllTests();
        model.addAttribute("tests",tests);
        return "test_main";
    }
    @GetMapping("/test/{id}/edit")
    public String testEdit(@PathVariable(value = "id") long id, Model model){

        Test test = testService.getTestById(id);
        model.addAttribute("test",test);

        List<Question> questions = questionService.getQuestionsById(id);
        model.addAttribute("questions",questions);

        ArrayList<QuestionModel> qm = testService.testViewById(id);
        if(qm==null){
            return "redirect:/test";
        }
        model.addAttribute("questionModels",qm);

        return "test_edit";
    }
    @PostMapping("/test/{id}/edit")
    public String testUpd(@PathVariable(value = "id") long id,@RequestParam Map<String, String> form){
        testService.EditTest(id,form);
        return "redirect:/test";
    }
    @PostMapping("/test/{id}/remove")
    public String testRemove(@PathVariable(value = "id") long id){
        testService.deleteTest(id);
        return "redirect:/test";
    }
    @GetMapping("/test/add")
    public String testAdd(Model model){
        Iterable<User> users = usersService.getAllUsers();
        model.addAttribute("users",users);
        return "test_add";
    }

    @PostMapping("/test/add")
    public String testPostAdd(@RequestParam Map<String, String> form,
                              @RequestParam String title,
                              @RequestParam String description){
        testService.addTest(form, title, description);
        return "redirect:/test";

    }
    @PostMapping("/test")
    public String testSearch(Model model,@RequestParam String date,@RequestParam String search){

        ArrayList<Test> TestsForModel = testService.testsSearch(date, search);
        model.addAttribute("tests",TestsForModel);
        return "test_main";
    }
    @GetMapping("/test/{id}")
    public String testView(@PathVariable(value = "id") long id,Model model){
        Test test = testService.getTestById(id);
        model.addAttribute("test",test);

        List<Question> questions = questionService.getQuestionsById(id);
        model.addAttribute("questions",questions);

        ArrayList<QuestionModel> qm = testService.testViewById(id);
        if(qm==null){
            return "redirect:/test";
        }
        model.addAttribute("questionModels",qm);
        return "test";
    }
    @PostMapping("/test/{id}")
    public String testResult(@PathVariable(value = "id") long id,Model model,@RequestParam Map<String, String> form){
        String message = testService.getTestResult(id, form);
        model.addAttribute("message",message);
        return "test_result";
    }
}
