package com.zhibo.kingsoft;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.util.Base64;
import java.util.Formatter;

/**
 * Created by jichao on 2016/11/15.
 */

public class KingSoftServiceImpl implements KingSoftService {

    public KingSoftServiceImpl() {
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    private String secretKey;

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    private String accessKey;

    public static String calculateRFC2104HMAC(String key, String data)
            throws SignatureException, NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException {
        final String HMAC_SHA1_ALGORITHM = "HmacSHA1";
        SecretKeySpec signingKey = new SecretKeySpec(key.getBytes("UTF-8"), HMAC_SHA1_ALGORITHM);
        Mac mac = Mac.getInstance(HMAC_SHA1_ALGORITHM);
        mac.init(signingKey);
        return Base64.getEncoder().encodeToString(mac.doFinal(data.getBytes("UTF-8")));
    }

    private static String toHexString(byte[] bytes) {
        Formatter formatter = new Formatter();
        for (byte b : bytes) {
            formatter.format("%02x", b);
        }
        return formatter.toString();
    }

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
    private String computeSignature(Long expire, PushStreamResource pushStreamResource) throws Exception {
        //GET\n1436976000\nnonce=4e1f2519c626cbfbab1520c255830c26&vdoid=12345
        String stringToSign = "GET\n" + expire + "\nnonce=" +
                pushStreamResource.getNonce() + "&vdoid=" + pushStreamResource.getVdoid();
        return calculateRFC2104HMAC(secretKey, stringToSign);
    }

    @Override
    public String constructPushUrl(String zhiboName, Long expire, String nonce, String vdoid) throws Exception {
        //Exmaple: rtmp://***.uplive.ks-cdn.com/live/stream?signature=vU9XqPLcXd3nWdlfLWIhruZrLAM%3D
        // &accesskey=P3UPCMORAFON76Q6RTNQ
        // &expire=1436976000
        // &nonce=4e1f2519c626cbfbab1520c255830c26
        // ＆vdoid=123456
        PushStreamResource pushStreamResource = new PushStreamResource();
        pushStreamResource.setVdoid(vdoid);
        pushStreamResource.setNonce(nonce);
        StringBuilder pushUrl = new StringBuilder();
        pushUrl.append("rtmp://");
        pushUrl.append(zhiboName);
        pushUrl.append(".uplive.ks-cdn.com/live/stream?signature=");
        pushUrl.append(computeSignature(expire, pushStreamResource));
        pushUrl.append("&accessKey=").append(accessKey);
        pushUrl.append("&expire=").append(expire);
        pushUrl.append("&nonce=").append(nonce);
        pushUrl.append("&vdoid=").append(vdoid);
        return pushUrl.toString();

    }
}
