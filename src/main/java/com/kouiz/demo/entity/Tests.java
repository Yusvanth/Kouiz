package com.kouiz.demo.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Tests")
public class Tests {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "related_topics")
    private String relatedTopics;

    @Column(name = "registration_fee")
    private double registrationFee;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "start_time")
    private Date startTime;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "end_time")
    private Date endTime;

    @Column(name = "average_score")
    private double averageScore;

    @ManyToMany(mappedBy = "registeredTests",fetch = FetchType.EAGER)
    private List<User> registeredUsers;

    @ManyToMany(mappedBy = "completedTests",fetch = FetchType.EAGER)
    private List<User> completedUsers;

    @JsonManagedReference
    @OneToMany(mappedBy = "test",cascade = CascadeType.ALL)
    private List<Questions> questions;

    @JsonManagedReference
    @OneToMany(mappedBy = "test",cascade = CascadeType.ALL)
    private List<Result> results;

    public Tests(){

    }

    public Tests(String name, String relatedTopics, double registrationFee, Date startTime, Date endTime, double averageScore, List<User> registeredUsers, List<User> completedUsers, List<Questions> questions, List<Result> results) {
        this.name = name;
        this.relatedTopics = relatedTopics;
        this.registrationFee = registrationFee;
        this.startTime = startTime;
        this.endTime = endTime;
        this.averageScore = averageScore;
        this.registeredUsers = registeredUsers;
        this.completedUsers = completedUsers;
        this.questions = questions;
        this.results = results;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public double getAverageScore() {
        return averageScore;
    }

    public void setAverageScore(double averageScore) {
        this.averageScore = averageScore;
    }

    public List<User> getRegisteredUsers() {
        return registeredUsers;
    }

    public void setRegisteredUsers(List<User> registeredUsers) {
        this.registeredUsers = registeredUsers;
    }

    public List<User> getCompletedUsers() {
        return completedUsers;
    }

    public void setCompletedUsers(List<User> completedUsers) {
        this.completedUsers = completedUsers;
    }

    public List<Questions> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Questions> questions) {
        this.questions = questions;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }
}
