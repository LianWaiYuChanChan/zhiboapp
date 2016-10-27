package com.zhibo.infra;

import org.springframework.http.HttpStatus;

/**
 * Created by jichao on 2016/10/27.
 */
public class ResourceNotFoundException extends ZhiBoBaseException {
    public ResourceNotFoundException(HttpStatus statusCode, String msg) {
        super(statusCode, msg);
    }

    public ResourceNotFoundException(String resourceName, String resourceId) {
        super(HttpStatus.NOT_FOUND, "Cannot find resource " + resourceName + " with id = " + resourceId);
    }

}
