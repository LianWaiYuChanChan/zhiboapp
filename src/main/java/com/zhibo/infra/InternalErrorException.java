package com.zhibo.infra;

import org.springframework.http.HttpStatus;

/**
 * Created by jichao on 2016/10/25.
 */
public class InternalErrorException extends ZhiBoBaseException {
    public InternalErrorException(HttpStatus statusCode, String msg) {
        super(statusCode, msg);
    }
}
