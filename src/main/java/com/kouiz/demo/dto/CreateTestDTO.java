package com.kouiz.demo.dto;

import java.util.Date;
import java.util.List;

public class CreateTestDTO {

    private String name;

    private String relatedTopics;

    private double registrationFee;

    private Date startTime;

    private Date endTime;

    private List<QuestionDTO> questions;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRelatedTopics() {
        return relatedTopics;
    }

    public void setRelatedTopics(String relatedTopics) {
        this.relatedTopics = relatedTopics;
    }

    public double getRegistrationFee() {
        return registrationFee;
    }

    public void setRegistrationFee(double registrationFee) {
        this.registrationFee = registrationFee;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public List<QuestionDTO> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionDTO> questions) {
        this.questions = questions;
    }

    @Override
    public String toString() {
        return "CreateTestDTO{" +
                "name='" + name + '\'' +
                ", relatedTopics='" + relatedTopics + '\'' +
                ", registrationFee=" + registrationFee +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", questions=" + questions +
                '}';
    }
}
