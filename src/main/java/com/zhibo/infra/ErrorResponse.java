package com.zhibo.infra;

/**
 * Created by jichao on 2016/10/23.
 */
public class ErrorResponse implements  ResponseObject {

    public ErrorResponse(ZhiBoBaseException zhiBoException) {
        this.statusCode = zhiBoException.getStatusCode().value();
        this.errorMessage = zhiBoException.getMessage();
    }

    public ErrorResponse(int status, String msg) {
        this.statusCode = status;
        this.errorMessage = msg;
    }

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
