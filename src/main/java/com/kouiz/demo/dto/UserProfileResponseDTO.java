package com.kouiz.demo.dto;

public class UserProfileResponseDTO {

    private String name;

    private String email;

    private int noOfTests;

    private int noOfTestsRegistered;

    private int noOfTestsCompleted;

    public int getNoOfTests() {
        return noOfTests;
    }

    public void setNoOfTests(int noOfTests) {
        this.noOfTests = noOfTests;
    }

    public int getNoOfTestsRegistered() {
        return noOfTestsRegistered;
    }

    public void setNoOfTestsRegistered(int noOfTestsRegistered) {
        this.noOfTestsRegistered = noOfTestsRegistered;
    }

    public int getNoOfTestsCompleted() {
        return noOfTestsCompleted;
    }

    public void setNoOfTestsCompleted(int noOfTestsCompleted) {
        this.noOfTestsCompleted = noOfTestsCompleted;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
