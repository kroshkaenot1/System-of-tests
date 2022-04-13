package com.rgr.system_of_tests.controllers;

import com.rgr.system_of_tests.repo.AnswerRepository;
import com.rgr.system_of_tests.repo.QuestionRepository;
import com.rgr.system_of_tests.repo.models.Answer;
import com.rgr.system_of_tests.repo.models.Question;
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
import java.util.*;

@Controller
public class Testcontroller {
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private AnswerRepository answerRepository;
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
    public String testPostAdd(@RequestParam Map<String, String> form,@RequestParam String title,@RequestParam String description,Model model){
        Test test = new Test(title,description);
        testsRepository.save(test);
        int q_count = 1;
        int a_count = 1;
        Long last_id_q = null;
        int ball = 0;
        for(String key : form.keySet()){
            if(key.equals("a"+q_count+a_count)){
                try{
                    ball = Integer.parseInt(form.get("b"+q_count+a_count));
                }catch (NumberFormatException e){
                    model.addAttribute("message","Некорректно введены баллы за ответ!");
                    return "test_add";
                }
                Answer answer = new Answer(last_id_q,form.get(key),ball);
                answerRepository.save(answer);
                a_count++;
                if(a_count==3){
                    if(!form.containsKey("a"+q_count+a_count)){q_count++; a_count=1;}
                }
                if(a_count==4){a_count=1;q_count++;}
            }
            if(key.equals("q"+q_count)){
                Question question = new Question(test.getId(),form.get(key));
                questionRepository.save(question);
                last_id_q =question.getId();
            }
        }
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
