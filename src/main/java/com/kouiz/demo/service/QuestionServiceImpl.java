package com.kouiz.demo.service;

import com.kouiz.demo.dto.OptionDTO;
import com.kouiz.demo.dto.QuestionDTO;
import com.kouiz.demo.entity.Option;
import com.kouiz.demo.entity.Questions;
import com.kouiz.demo.entity.Tests;
import com.kouiz.demo.repository.QuestionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService{

    @Autowired
    OptionService optionService;

    @Autowired
    QuestionRepo questionRepo;

    @Override
    public Questions saveQuestion(QuestionDTO questionDTO, Tests tests) {

        Questions question = new Questions();
        question.setQuestion(questionDTO.getQuestion());
        List<Option> options = new ArrayList<>();
        for(OptionDTO givenOption:questionDTO.getOptions()){
            System.out.println(givenOption);
            Option option = optionService.saveOption(givenOption,question);
            option.setQuestion(question);
//            option=optionService.saveChanges(option);
            options.add(option);
        }
        question.setOptions(options);
        question.setTest(tests);
        return questionRepo.save(question);
    }

    @Override
    public Questions saveChanges(Questions questions) {
        return questionRepo.save(questions);
    }

}
