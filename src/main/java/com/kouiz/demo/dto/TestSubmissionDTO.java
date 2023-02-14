package com.kouiz.demo.dto;

import java.util.List;

public class TestSubmissionDTO {

    private int testId;

    private String testName;

    private List<QuestionResponseDTO> response;

    public TestSubmissionDTO(int testId, String testName, List<QuestionResponseDTO> response) {
        this.testId = testId;
        this.testName = testName;
        this.response = response;
    }

    public int getTestId() {
        return testId;
    }

    public void setTestId(int testId) {
        this.testId = testId;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public List<QuestionResponseDTO> getResponse() {
        return response;
    }

    public void setResponse(List<QuestionResponseDTO> response) {
        this.response = response;
    }
}
