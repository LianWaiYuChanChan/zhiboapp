package com.zhibo.kingsoft;

/**
 * Created by jichao on 2016/11/15.
 */
public interface KingSoftService {
    /**
     * More details; https://v.ksyun.com/doc.html#/doc/livesdk.md
     * Signature = Base64(HMAC-SHA1(SecretKey, UTF-8-Encoding-Of(StringToSign)))
     * StringToSign:
     * GET+”\n”+Expire+”\n”+Resource
     * GET\n1436976000\nnonce=4e1f2519c626cbfbab1520c255830c26&vdoid=12345
     * expire:签名的过期时间，Linux UTC标准时间戳，如1141889120，超过此时间的URL将无法使用
     *
     * @return
     */
    String computeSignature(Long expire, PushStreamResource resource) throws Exception;
}
