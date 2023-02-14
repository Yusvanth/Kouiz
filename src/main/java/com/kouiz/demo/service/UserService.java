package com.kouiz.demo.service;

import com.kouiz.demo.dto.SignupDTO;
import com.kouiz.demo.entity.User;
import jakarta.servlet.http.HttpServletRequest;

public interface UserService {

    public boolean saveUser(SignupDTO signupDTO);
    public User findByEmail(String email);

    public User getCurrentUserDetails();

    public void saveChanges(User user);

}
