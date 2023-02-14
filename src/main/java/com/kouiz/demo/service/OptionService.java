package com.kouiz.demo.service;

import com.kouiz.demo.dto.OptionDTO;
import com.kouiz.demo.entity.Option;
import com.kouiz.demo.entity.Questions;

public interface OptionService {

    public Option saveOption(OptionDTO optionDTO, Questions questions);

    public Option saveChanges(Option option);
}
