package com.kouiz.demo.dto;

public class QuestionResponseDTO {

    private int id;

    private int selectedOptionId;

    public QuestionResponseDTO(int id, int selectedOptionId) {
        this.id = id;
        this.selectedOptionId = selectedOptionId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSelectedOptionId() {
        return selectedOptionId;
    }

    public void setSelectedOptionId(int selectedOptionId) {
        this.selectedOptionId = selectedOptionId;
    }
}
