package com.kouiz.demo.service;

import com.kouiz.demo.dto.AuthenticationResponseDTO;
import com.kouiz.demo.dto.LoginDTO;
import com.kouiz.demo.entity.RefreshToken;
import com.kouiz.demo.entity.User;
import com.kouiz.demo.handlers.ApiException;
import com.kouiz.demo.util.JwtUtils;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class LoginMapper {
    @Autowired
    UserService userService;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UserServiceMapper userDetailsService;
    @Autowired
    private JwtUtils jwtTokenUtil;

    @Autowired
    RefreshTokenService refreshTokenService;
    public AuthenticationResponseDTO createAuthenticationToken(LoginDTO authenticationRequest, HttpServletResponse response) throws ApiException {

        try
        {

            final UserDetails userDetails = userDetailsService
                    .loadUserByUsername(authenticationRequest.getEmail());
            User user = userService.findByEmail(authenticationRequest.getEmail());

            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword())
            );
            String jwt = jwtTokenUtil.generateJwt(user.getEmail());
            RefreshToken refreshToken = refreshTokenService.createRefreshToken(user.getId());
            return new AuthenticationResponseDTO(jwt,refreshToken.getToken());
        }
        catch(UsernameNotFoundException e)
        {
            throw new UsernameNotFoundException("Username not found");
        }
        catch (Exception e){
            throw new ApiException("Invalid password", HttpStatus.BAD_REQUEST);
        }
    }

}
