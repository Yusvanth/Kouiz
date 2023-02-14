package com.kouiz.demo.service;

import com.kouiz.demo.dto.CreateTestDTO;
import com.kouiz.demo.dto.QuestionDTO;
import com.kouiz.demo.dto.AllTestDetailsDTO;
import com.kouiz.demo.entity.Questions;
import com.kouiz.demo.entity.Tests;
import com.kouiz.demo.repository.TestRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TestServiceImpl implements TestService{

    @Autowired
    QuestionService questionService;

    @Autowired
    TestRepo testRepo;

    @Override
    public Tests saveTest(CreateTestDTO createTestDTO) {
        Tests tests = new Tests();
        tests.setName(createTestDTO.getName());
        tests.setStartTime(createTestDTO.getStartTime());
        tests.setEndTime(createTestDTO.getEndTime());
        tests.setRelatedTopics(createTestDTO.getRelatedTopics());
        tests.setRegistrationFee(createTestDTO.getRegistrationFee());
        tests.setRegisteredUsers(new ArrayList<>());
        tests.setCompletedUsers(new ArrayList<>());
        tests.setResults(new ArrayList<>());
        List<Questions> questions=new ArrayList<>();
        for(QuestionDTO givenQuestion:createTestDTO.getQuestions()){
            Questions question = questionService.saveQuestion(givenQuestion,tests);
            question.setTest(tests);
            //question = questionService.saveChanges(question);
            questions.add(question);
        }
        tests.setQuestions(questions);
        return testRepo.save(tests);
    }

    @Override
    public List<AllTestDetailsDTO> getAllTestDetails() {
        List<Tests> tests = testRepo.findAll();
        List<AllTestDetailsDTO> result = new ArrayList<>();
        for(Tests test:tests){
            AllTestDetailsDTO allTestDetailsDTO = new AllTestDetailsDTO();
            allTestDetailsDTO.setId(test.getId());
            allTestDetailsDTO.setName(test.getName());
            allTestDetailsDTO.setEndTime(test.getEndTime());
            allTestDetailsDTO.setStartTime(test.getStartTime());
            allTestDetailsDTO.setRelatedTopics(test.getRelatedTopics());
            allTestDetailsDTO.setAverageScore(test.getAverageScore());
            result.add(allTestDetailsDTO);
        }
        return result;
    }

    @Override
    public Tests getTestById(int id) {
        return testRepo.findById(id);
    }

    @Override
    public void saveChanges(Tests tests) {
        testRepo.save(tests);
    }

    @Override
    public int getNoOfTests() {
        return testRepo.findAll().size();
    }


}
