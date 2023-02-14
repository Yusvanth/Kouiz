package com.kouiz.demo.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class ResponseHandler {

    public static ResponseEntity<Object> generateResponse(String msg, HttpStatus status){
        Map<String,Object> map = new HashMap<>();
        map.put("msg",msg);
        map.put("status",status);
        return new ResponseEntity<>(map,status);
    }

    public static ResponseEntity<Object> generateResponse(String msg, HttpStatus status,Object object){
        Map<String,Object> map = new HashMap<>();
        map.put("msg",msg);
        map.put("status",status);
        map.put("data",object);
        return new ResponseEntity<>(map,status);
    }

}
