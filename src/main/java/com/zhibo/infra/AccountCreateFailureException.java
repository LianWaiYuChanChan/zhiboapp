package com.zhibo.infra;

import org.springframework.http.HttpStatus;

/**
 * Created by jichao on 2016/10/25.
 */
public class AccountCreateFailureException extends ZhiBoBaseException {

    public AccountCreateFailureException(HttpStatus statusCode, String msg) {
        super(statusCode, msg);
    }
}
