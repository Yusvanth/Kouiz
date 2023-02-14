package com.kouiz.demo.service;

import com.kouiz.demo.dto.QuestionResponseDTO;
import com.kouiz.demo.dto.TestSubmissionDTO;
import com.kouiz.demo.entity.*;
import com.kouiz.demo.repository.ResultRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResultServiceImpl implements ResultService{

    @Autowired
    TestService testService;

    @Autowired
    ResultRepo resultRepo;

    @Autowired
    UserService userService;


    @Override
    public void handleSubmission(TestSubmissionDTO testSubmissionDTO, User loggedInUser) {

        Tests test = testService.getTestById(testSubmissionDTO.getTestId());

        List<Questions> originalTestQuestions = test.getQuestions();

        List<QuestionResponseDTO> response = testSubmissionDTO.getResponse();

        int noOfCorrectAnswers=0;

        for(Questions actualQuestion : originalTestQuestions)
            for(QuestionResponseDTO userResponse : response)
                if(actualQuestion.getId() == userResponse.getId())
                    for(Option option : actualQuestion.getOptions())
                        if(option.isCorrect()&&option.getId()== userResponse.getId())
                            noOfCorrectAnswers++;


        System.out.println(noOfCorrectAnswers);
        Result result = new Result();
        result.setTest(test);
        result.setScore(noOfCorrectAnswers);
        result.setUser(loggedInUser);
        resultRepo.save(result);

        List<Tests> completedTestsOfLoggedInUser = loggedInUser.getCompletedTests();
        completedTestsOfLoggedInUser.add(test);
        loggedInUser.setCompletedTests(completedTestsOfLoggedInUser);
        userService.saveChanges(loggedInUser);
    }

    @Override
    public List<Result> getResultsOfUser(User user) {
        return user.getResults();
    }


}
