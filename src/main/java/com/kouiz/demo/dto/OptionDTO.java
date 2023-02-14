package com.kouiz.demo.dto;

public class OptionDTO {

    private String name;

    private Boolean isCorrect;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setCorrect(boolean correct) {
        isCorrect = correct;
    }

    @Override
    public String toString() {
        return "OptionDTO{" +
                "name='" + name + '\'' +
                ", isCorrect=" + isCorrect +
                '}';
    }
}
