package com.rgr.system_of_tests.service;

import com.rgr.system_of_tests.repo.QuestionRepository;
import com.rgr.system_of_tests.repo.models.Question;
import com.rgr.system_of_tests.repo.models.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {
    @Autowired
    private QuestionRepository questionRepository;
    public List<Question> getQuestionsById(long id){
        List<Question> questions = questionRepository.findByTestId(id);
        return questions;
    }
}
