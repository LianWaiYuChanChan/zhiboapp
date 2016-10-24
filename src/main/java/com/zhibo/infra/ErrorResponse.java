package com.zhibo.infra;

/**
 * Created by jichao on 2016/10/23.
 */
public class ErrorResponse {
    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    private int statusCode;
    private String errorMessage;
}
