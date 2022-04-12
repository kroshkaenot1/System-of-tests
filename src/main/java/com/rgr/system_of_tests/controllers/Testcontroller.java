package com.rgr.system_of_tests.controllers;

import com.rgr.system_of_tests.repo.models.Test;
import com.rgr.system_of_tests.repo.TestsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Optional;

@Controller
public class Testcontroller {
    @Autowired
    private TestsRepository testsRepository;
    @GetMapping("/")
    public String home(){
        return "home";
    }
    @GetMapping("/test")
    public String testMain(Model model){
        Iterable<Test> tests = testsRepository.findAll();
        model.addAttribute("tests",tests);
        return "test_main";
    }
    @GetMapping("/test/{id}/edit")
    public String testEdit(@PathVariable(value = "id") long id, Model model){
        if(!testsRepository.existsById(id)){
            return "redirect:/test";
        }
        Optional<Test> test = testsRepository.findById(id);
        ArrayList<Test> res = new ArrayList<>();
        test.ifPresent(res::add);
        model.addAttribute("test",res);
        return "test_edit";
    }
    @PostMapping("/test/{id}/edit")
    public String testUpd(@PathVariable(value = "id") long id, @RequestParam String title,@RequestParam String description){
        Test test = testsRepository.findById(id).orElseThrow();
        test.setTitle(title);
        test.setDescription(description);
        testsRepository.save(test);
        return "redirect:/test";
    }
    @PostMapping("/test/{id}/remove")
    public String testRemove(@PathVariable(value = "id") long id){

        Test test = testsRepository.findById(id).orElseThrow();
        testsRepository.delete(test);
        return "redirect:/test";
    }
    @GetMapping("/test/add")
    public String testAdd(){return "test_add";}
    @PostMapping("/test/add")
    public String testPostAdd(@RequestParam String title,@RequestParam String description){
        Test test = new Test(title,description);
        testsRepository.save(test);
        return "redirect:/test";
    }
    @PostMapping("/test")
    public String testSearch(Model model,@RequestParam String date,@RequestParam String search)throws ParseException {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        Date date1 = format.parse(date);
        ArrayList<Test> resTests = testsRepository.findByDate(date1);
        ArrayList<Test> TestsForModel = new ArrayList<>();
        for(Test t:resTests){
            if(t.getTitle().toLowerCase().contains(search.toLowerCase()) || t.getDescription().toLowerCase().contains(search.toLowerCase())){
                TestsForModel.add(t);
            }
        }
        model.addAttribute("tests",TestsForModel);
        return "test_main";
    }
}
