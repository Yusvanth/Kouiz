package com.kouiz.demo.dto;

import java.util.List;

public class QuestionDTO {

    private String question;

    private List<OptionDTO> options;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<OptionDTO> getOptions() {
        return options;
    }

    public void setOptions(List<OptionDTO> options) {
        this.options = options;
    }
}
