package com.kouiz.demo.dto;

import java.util.Date;
import java.util.List;

public class TestDetailDTO {

    private String name;

    private Date startDate;

    private Date endDate;

    private List<QuestionDTO> questions;

    public TestDetailDTO(String name, Date startDate, Date endDate, List<QuestionDTO> questions) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.questions = questions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public List<QuestionDTO> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionDTO> questions) {
        this.questions = questions;
    }
}
