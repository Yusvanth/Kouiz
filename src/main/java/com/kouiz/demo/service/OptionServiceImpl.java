package com.kouiz.demo.service;

import com.kouiz.demo.dto.OptionDTO;
import com.kouiz.demo.entity.Option;
import com.kouiz.demo.entity.Questions;
import com.kouiz.demo.repository.OptionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OptionServiceImpl implements OptionService{

    @Autowired
    OptionRepo optionRepo;

    @Override
    public Option saveOption(OptionDTO optionDTO, Questions questions) {
        Option option = new Option();
        option.setName(optionDTO.getName());
        //System.out.println(optionDTO.isCorrect());
        option.setCorrect(optionDTO.isCorrect());
        option.setQuestion(questions);
        return optionRepo.save(option);
    }

    @Override
    public Option saveChanges(Option option) {
        return optionRepo.save(option);
    }

}
