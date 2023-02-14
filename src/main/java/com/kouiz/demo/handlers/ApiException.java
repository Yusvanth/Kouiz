package com.kouiz.demo.handlers;

import org.springframework.http.HttpStatus;

public class ApiException extends Exception{

    private String code;
    private HttpStatus status;


    public ApiException(String code,HttpStatus status){
        super(code);
        this.code=code;
        this.status=status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }
}
