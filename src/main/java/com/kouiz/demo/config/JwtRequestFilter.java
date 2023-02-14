package com.kouiz.demo.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kouiz.demo.handlers.ApiException;
import com.kouiz.demo.handlers.ExceptionResponseHandler;
import com.kouiz.demo.util.JwtUtils;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {


    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    JwtUtils jwtUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {



        String authorization = request.getHeader("Authorization");
        String username=null;
        String jwt=null;
        //System.out.println("jwt"+" "+authorization);
        if(authorization!=null&&authorization.startsWith("Bearer ")) {
            //System.out.println("inside bearer");
            jwt=authorization.substring(7);
            username=jwtUtils.extractUserName(jwt);
        }
        System.out.println(jwt+" \n"+username);
        if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
            try {
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                if (jwtUtils.validateToken(jwt, userDetails)) {
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());
                    usernamePasswordAuthenticationToken
                            .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                }
            } catch (UsernameNotFoundException e) {
                throw new UsernameNotFoundException("Username not found");
            } catch (ExpiredJwtException e) {
                try {
                    throw new ApiException("TA2", HttpStatus.REQUEST_TIMEOUT);
                } catch (ApiException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
        if(jwtUtils.legitJwt(jwt)) {
            handleInvalidJWTRequest(response);
            return;
        }

        filterChain.doFilter(request, response);
    }
    private void handleInvalidJWTRequest(HttpServletResponse response) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        ExceptionResponseHandler errorResponse = new ExceptionResponseHandler();
        errorResponse.setErrorMessgae("JWT logged out");
        errorResponse.setErrorCode("TA10");
        errorResponse.setStatus(403);
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
    }
}
