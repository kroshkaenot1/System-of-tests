package com.rgr.system_of_tests.repo.models;

public class QuestionModel {
    private String question_text;
    private String answer1,answer2,answer3;
    private Long answId1,answId2,answId3;
    private Long q_id;

    public QuestionModel(String question_text, String answer1, String answer2, String answer3, Long answId1, Long answId2, Long answId3, Long q_id) {
        this.question_text = question_text;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.answer3 = answer3;
        this.answId1 = answId1;
        this.answId2 = answId2;
        this.answId3 = answId3;
        this.q_id = q_id;
    }

    public Long getQ_id() {
        return q_id;
    }

    public void setQ_id(Long q_id) {
        this.q_id = q_id;
    }

    public Long getAnswId1() {
        return answId1;
    }

    public void setAnswId1(Long answId1) {
        this.answId1 = answId1;
    }

    public Long getAnswId2() {
        return answId2;
    }

    public void setAnswId2(Long answId2) {
        this.answId2 = answId2;
    }

    public Long getAnswId3() {
        return answId3;
    }

    public void setAnswId3(Long answId3) {
        this.answId3 = answId3;
    }

    public QuestionModel() {
    }

    public String getQuestion_text() {
        return question_text;
    }

    public void setQuestion_text(String question_text) {
        this.question_text = question_text;
    }

    public String getAnswer1() {
        return answer1;
    }

    public void setAnswer1(String answer1) {
        this.answer1 = answer1;
    }

    public String getAnswer2() {
        return answer2;
    }

    public void setAnswer2(String answer2) {
        this.answer2 = answer2;
    }

    public String getAnswer3() {
        return answer3;
    }

    public void setAnswer3(String answer3) {
        this.answer3 = answer3;
    }
}
