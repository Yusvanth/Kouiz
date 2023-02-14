package com.kouiz.demo.service;

import com.kouiz.demo.dto.CreateTestDTO;
import com.kouiz.demo.dto.AllTestDetailsDTO;
import com.kouiz.demo.entity.Tests;

import java.util.List;

public interface TestService {

    public Tests saveTest(CreateTestDTO createTestDTO);

    public List<AllTestDetailsDTO> getAllTestDetails();

    public Tests getTestById(int id);

    public void saveChanges(Tests tests);

    public int getNoOfTests();
}
