package com.kouiz.demo.service;

import com.kouiz.demo.dto.QuestionDTO;
import com.kouiz.demo.entity.Questions;
import com.kouiz.demo.entity.Tests;

import java.util.List;

public interface QuestionService {

    public Questions saveQuestion(QuestionDTO questionDTO, Tests tests);

    public Questions saveChanges(Questions questions);

}
