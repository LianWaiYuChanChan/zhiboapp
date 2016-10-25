package com.zhibo.infra;

import org.springframework.http.HttpStatus;

/**
 * Created by jichao on 2016/10/25.
 */
public class ZhiBoBaseException extends Exception{

    public ZhiBoBaseException(HttpStatus statusCode, String msg) {
        this.statusCode = statusCode;
        this.message = msg;
    }

    public HttpStatus getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(HttpStatus statusCode) {
        this.statusCode = statusCode;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    private HttpStatus statusCode;
    private String message;
}
