package com.kouiz.demo.controller;

import com.kouiz.demo.dto.*;
import com.kouiz.demo.entity.*;
import com.kouiz.demo.handlers.ApiException;
import com.kouiz.demo.handlers.ResponseHandler;
import com.kouiz.demo.service.LoginMapper;
import com.kouiz.demo.service.ResultService;
import com.kouiz.demo.service.TestService;
import com.kouiz.demo.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class HomeController {

    @Autowired
    UserService userService;

    @Autowired
    TestService testService;

    @Autowired
    LoginMapper loginMapper;

    @Autowired
    ResultService resultService;

    @PostMapping("/sign-up")
    public ResponseEntity<Object> signUp(@RequestBody SignupDTO signupDTO){

        if(userService.saveUser(signupDTO))
            return ResponseHandler.generateResponse("User signed up !", HttpStatus.OK);
        else
            return ResponseHandler.generateResponse("User not signed up",HttpStatus.BAD_REQUEST);

    }

    @PostMapping("/log-in")
    public ResponseEntity<Object> logIn(@RequestBody LoginDTO loginDTO, HttpServletResponse httpServletResponse) throws ApiException {
        User user = userService.findByEmail(loginDTO.getEmail());
        if(user==null){
            return ResponseHandler.generateResponse("User not found , please sign up again!!!",HttpStatus.BAD_REQUEST);
        }
        // Authenticate user
        AuthenticationResponseDTO responseDto = loginMapper.createAuthenticationToken(loginDTO, httpServletResponse);

        if (responseDto != null)
            return ResponseHandler.generateResponse("Login Successful", HttpStatus.OK, responseDto);
        else
            return ResponseHandler.generateResponse("Incorrect Password",HttpStatus.EXPECTATION_FAILED);

    }

    @PostMapping("/save-test")
    public ResponseEntity<Object> createTest(@RequestBody CreateTestDTO createTestDTO){
        Tests test = testService.saveTest(createTestDTO);
        if(test!=null){
            return ResponseHandler.generateResponse("Test added !",HttpStatus.OK);
        }
        else
            return ResponseHandler.generateResponse("Test not added !",HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/get-user-profile")
    public ResponseEntity<Object> getUserProfile(){
        User user = userService.getCurrentUserDetails();
        UserProfileResponseDTO userProfileResponseDTO = new UserProfileResponseDTO();
        userProfileResponseDTO.setEmail(user.getEmail());
        userProfileResponseDTO.setName(user.getName());
        userProfileResponseDTO.setNoOfTestsCompleted(user.getCompletedTests().size());
        userProfileResponseDTO.setNoOfTestsRegistered(user.getRegisteredTests().size());
        userProfileResponseDTO.setNoOfTests(testService.getNoOfTests());
        return ResponseHandler.generateResponse("User details retrieved",HttpStatus.OK,userProfileResponseDTO);
    }

    @GetMapping("/get-all-tests")
    public ResponseEntity<Object> getAllTest(){
        List<AllTestDetailsDTO> tests = testService.getAllTestDetails();
        return ResponseHandler.generateResponse("All tests retrieved",HttpStatus.OK,tests);
    }

    @GetMapping("/get-test")
    public ResponseEntity<Object> getTest(@RequestParam("testId") int id){
        Tests test = testService.getTestById(id);

        List<Questions> questions = test.getQuestions();
        List<QuestionsDTO> questionDTOList = new ArrayList<>();
        System.out.println("size:"+test);
        for(Questions question : questions){
            System.out.println(questionDTOList);
            QuestionsDTO questionDTO = new QuestionsDTO();
            questionDTO.setQuestion(question.getQuestion());
            questionDTO.setId(question.getId());
            List<Option> options = question.getOptions();
            List<OptionsDTO> optionDTOS = new ArrayList<>();
            for(Option option : options){
                optionDTOS.add(new OptionsDTO(option.getName(),option.getId()));
            }
            questionDTO.setOptions(optionDTOS);
            questionDTOList.add(questionDTO);
        }
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        UserDetailsDTO userDetailsDTO = (UserDetailsDTO) auth.getPrincipal();

        User loggedInUser = userService.findByEmail(userDetailsDTO.getEmail());

        List<Tests> registeredTestsOfLoggedInUser = loggedInUser.getRegisteredTests();
        registeredTestsOfLoggedInUser.add(test);
        userService.saveChanges(loggedInUser);

        return ResponseHandler.generateResponse("Questions retrieved for the requested question",HttpStatus.OK,questionDTOList);
    }

    @PostMapping("/submit-test")
    public ResponseEntity<Object> handleSubmission(@RequestBody TestSubmissionDTO testSubmissionDTO){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        UserDetailsDTO userDetailsDTO = (UserDetailsDTO) auth.getPrincipal();

        User loggedInUser = userService.findByEmail(userDetailsDTO.getEmail());

        resultService.handleSubmission(testSubmissionDTO,loggedInUser);

        return ResponseHandler.generateResponse("Test Submitted",HttpStatus.OK);
    }

    @GetMapping("/get-user-test-history")
    public ResponseEntity<Object> getUserTestHistory(){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        UserDetailsDTO userDetailsDTO = (UserDetailsDTO) auth.getPrincipal();

        User loggedInUser = userService.findByEmail(userDetailsDTO.getEmail());

        List<UserTestHistoryDTO> userTestHistoryDTOS = new ArrayList<>();

        List<Result> resultList = loggedInUser.getResults();

        for(Result result:resultList){
            String testName = result.getTest().getName();
            double averageScore = result.getTest().getAverageScore();
            UserTestHistoryDTO userTestHistoryDTO = new UserTestHistoryDTO();
            userTestHistoryDTO.setTestName(testName);
            userTestHistoryDTO.setScore(result.getScore());
            userTestHistoryDTO.setAverageScore(averageScore);
            userTestHistoryDTOS.add(userTestHistoryDTO);
        }

        return ResponseHandler.generateResponse("Test history retrieved",HttpStatus.OK,userTestHistoryDTOS);
    }

}