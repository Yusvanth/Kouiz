package com.kouiz.demo.service;

import com.kouiz.demo.dto.TestSubmissionDTO;
import com.kouiz.demo.entity.Result;
import com.kouiz.demo.entity.User;

import java.util.List;

public interface ResultService {

    public void handleSubmission(TestSubmissionDTO testSubmissionDTO, User loggedInUserEmail);

    public List<Result> getResultsOfUser(User user);
}
