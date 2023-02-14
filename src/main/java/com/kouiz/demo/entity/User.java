package com.kouiz.demo.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.hibernate.annotations.Fetch;

import java.util.List;

@Entity
@Table(name = "User")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "profile_picture")
    private String profilePicture;

    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(
            name = "registered_test_mapper",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "test_id")
    )
    private List<Tests> registeredTests;

    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(
            name = "completed_test_mapper",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "test_id")
    )
    private List<Tests> completedTests;

    @JsonManagedReference
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Result> results;

    public User(){

    }

    public User(String name, String email, String password, String profilePicture, List<Tests> registeredTests, List<Tests> completedTests, List<Result> results) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.profilePicture = profilePicture;
        this.registeredTests = registeredTests;
        this.completedTests = completedTests;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public List<Tests> getRegisteredTests() {
        return registeredTests;
    }

    public void setRegisteredTests(List<Tests> registeredTests) {
        this.registeredTests = registeredTests;
    }

    public List<Tests> getCompletedTests() {
        return completedTests;
    }

    public void setCompletedTests(List<Tests> completedTests) {
        this.completedTests = completedTests;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }
}
