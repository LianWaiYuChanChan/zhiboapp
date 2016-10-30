package com.zhibo.infra;

import org.junit.Test;
import org.junit.Assert;

/**
 * Created by jichao on 2016/10/29.
 */
public class ErrorResponseTest {

    @Test
    public void testErrorResponse() {
        ErrorResponse errorResponse = new ErrorResponse(500, "Internal Error");
        Assert.assertEquals(500, errorResponse.getStatusCode());
    }
}
