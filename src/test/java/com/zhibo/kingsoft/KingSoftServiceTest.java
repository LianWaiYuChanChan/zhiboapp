package com.zhibo.kingsoft;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.net.URL;
import java.time.Instant;

import static org.junit.Assert.assertEquals;

/**
 * Created by jichao on 2016/11/15.
 */
public class KingSoftServiceTest {
    @Test
    public void testConstructPushUrl() throws Exception {
        String secretKey = "Ik90eHJ6eElzZnBGakE3U3dQeklMd3k";
        String accessKey = "accessKeyValue";
        KingSoftServiceImpl kingSoftService = new KingSoftServiceImpl();
        kingSoftService.setSecretKey(secretKey);
        kingSoftService.setAccessKey(accessKey);

        Long expire = 1436976000L;
        String pushUrl = kingSoftService.constructPushUrl("zhiboName", expire, "nonceValue", "vdoidValue");
        System.out.println(pushUrl);
        URI uri = new URI(pushUrl);
        assertEquals("rtmp", uri.getScheme());
        MultiValueMap<String, String> parameters =
                UriComponentsBuilder.fromUri(new URI(pushUrl)).build().getQueryParams();
        assertEquals(1, parameters.get("nonce").size());
        assertEquals("nonceValue", parameters.getFirst("nonce"));

        assertEquals(1, parameters.get("vdoid").size());
        assertEquals("vdoidValue", parameters.getFirst("vdoid"));

        assertEquals(1, parameters.get("expire").size());
        assertEquals(expire.toString(), parameters.getFirst("expire"));

        assertEquals(1, parameters.get("expire").size());
        assertEquals(expire.toString(), parameters.getFirst("expire"));

        assertEquals(1, parameters.get("signature").size());
        assertEquals("pGH1sf6DhVpuzW91vPNZVlC/oNA=", parameters.getFirst("signature"));

        assertEquals(1, parameters.get("accessKey").size());
        assertEquals("accessKeyValue", parameters.getFirst("accessKey"));
    }
}
