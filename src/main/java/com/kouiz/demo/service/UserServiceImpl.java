package com.kouiz.demo.service;

import com.kouiz.demo.dto.SignupDTO;
import com.kouiz.demo.dto.UserDetailsDTO;
import com.kouiz.demo.entity.User;
import com.kouiz.demo.repository.UserRepo;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepo userRepo;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public boolean saveUser(SignupDTO signupDTO) {
        User user = new User();
        user.setEmail(signupDTO.getEmail());
        user.setName(signupDTO.getName());
        user.setResults(new ArrayList<>());
        user.setPassword(passwordEncoder.encode(signupDTO.getPassword()));
        user.setCompletedTests(new ArrayList<>());
        user.setRegisteredTests(new ArrayList<>());
        user.setProfilePicture("");
        if(userRepo.save(user)!=null)
            return true;
        return false;
    }

    public User findByEmail(String email){
        return userRepo.findByEmail(email);
    }

    @Override
    public User getCurrentUserDetails() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsDTO userDetailsDTO = (UserDetailsDTO) authentication.getPrincipal();

        return userRepo.findByEmail(userDetailsDTO.getEmail());
    }

    @Override
    public void saveChanges(User user) {
        userRepo.save(user);
    }
}
