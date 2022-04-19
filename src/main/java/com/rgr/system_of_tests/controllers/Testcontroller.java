package com.rgr.system_of_tests.controllers;

import com.rgr.system_of_tests.repo.models.Question;
import com.rgr.system_of_tests.repo.models.QuestionModel;
import com.rgr.system_of_tests.repo.models.Test;
import com.rgr.system_of_tests.repo.models.User;
import com.rgr.system_of_tests.service.QuestionService;
import com.rgr.system_of_tests.service.TestService;
import com.rgr.system_of_tests.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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
        Iterable<User> users = usersService.getAllUsers();
        model.addAttribute("users",users);
        return "test_edit";
    }
    @PostMapping("/test/{id}/edit")
    public String testUpd(@PathVariable(value = "id") long id,@RequestParam Map<String, String> form,
                          @RequestParam (value = "img1",required = false) MultipartFile img1,@RequestParam (value = "img2",required = false) MultipartFile img2,
                          @RequestParam (value = "img3",required = false) MultipartFile img3,@RequestParam (value = "img4",required = false) MultipartFile img4,
                          @RequestParam (value = "img5",required = false) MultipartFile img5,@RequestParam (value = "img6",required = false) MultipartFile img6,
                          @RequestParam (value = "img7",required = false) MultipartFile img7,@RequestParam (value = "img8",required = false) MultipartFile img8,
                          @RequestParam (value = "img9",required = false) MultipartFile img9,@RequestParam (value = "img10",required = false) MultipartFile img10) throws IOException {
        testService.EditTest(id, form, img1, img2, img3, img4, img5, img6, img7, img8, img9, img10);
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
                              @RequestParam (value = "img1",required = false) MultipartFile img1,@RequestParam (value = "img2",required = false) MultipartFile img2,
                              @RequestParam (value = "img3",required = false) MultipartFile img3,@RequestParam (value = "img4",required = false) MultipartFile img4,
                              @RequestParam (value = "img5",required = false) MultipartFile img5,@RequestParam (value = "img6",required = false) MultipartFile img6,
                              @RequestParam (value = "img7",required = false) MultipartFile img7,@RequestParam (value = "img8",required = false) MultipartFile img8,
                              @RequestParam (value = "img9",required = false) MultipartFile img9,@RequestParam (value = "img10",required = false) MultipartFile img10) throws IOException {
        testService.addTest(form,img1,img2,img3,img4,img5,img6,img7,img8,img9,img10);
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
