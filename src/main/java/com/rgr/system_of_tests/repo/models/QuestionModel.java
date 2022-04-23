package com.rgr.system_of_tests.repo.models;

public class QuestionModel {
    private String question_text;
    private String answer1,answer2,answer3;
    private Long answId1,answId2,answId3;
    private Long q_id;
    private String fileName;
    private int score1,score2,score3;

    public QuestionModel(String question_text, String answer1, String answer2, String answer3, Long answId1, Long answId2, Long answId3,
                         Long q_id, String fileName, int score1, int score2, int score3) {
        this.question_text = question_text;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.answer3 = answer3;
        this.answId1 = answId1;
        this.answId2 = answId2;
        this.answId3 = answId3;
        this.q_id = q_id;
        this.fileName = fileName;
        this.score1 = score1;
        this.score2 = score2;
        this.score3 = score3;
    }

    public int getScore1() {
        return score1;
    }

    public void setScore1(int score1) {
        this.score1 = score1;
    }

    public int getScore2() {
        return score2;
    }

    public void setScore2(int score2) {
        this.score2 = score2;
    }

    public int getScore3() {
        return score3;
    }

    public void setScore3(int score3) {
        this.score3 = score3;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
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
