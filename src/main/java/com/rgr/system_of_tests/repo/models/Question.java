package com.rgr.system_of_tests.repo.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long test_id;
    private String question_text;
    private String filename;
    public Question(Long test_id, String question_text) {
        this.test_id = test_id;
        this.question_text = question_text;
    }

    public Question() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTest_id() {
        return test_id;
    }

    public void setTest_id(Long test_id) {
        this.test_id = test_id;
    }

    public String getQuestion_text() {
        return question_text;
    }

    public void setQuestion_text(String question_text) {
        this.question_text = question_text;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
}
