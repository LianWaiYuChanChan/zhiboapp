package com.zhibo.kingsoft;

import org.junit.Assert;
import org.junit.Test;

import java.time.Instant;

/**
 * Created by jichao on 2016/11/15.
 */
public class KingSoftServiceTest {
    @Test
    public void testComputerSignature() throws Exception {
        String secretKey = "Ik90eHJ6eElzZnBGakE3U3dQeklMd3k";
        KingSoftService kingSoftService = new KingSoftServiceImpl(secretKey);
        //Long expire = Instant.now().getEpochSecond() + 5 * 60 * 60;
        Long expire = 1436976000L;
        PushStreamResource pushStreamResource = new PushStreamResource();
        pushStreamResource.setNonce("4e1f2519c626cbfbab1520c255830c26");
        pushStreamResource.setVdoid("12345");
        String signature = kingSoftService.computeSignature(expire, pushStreamResource);
        Assert.assertEquals("5WAPQa69A46KIz0WRXR4EsMV0w8=", signature);

    }
}
