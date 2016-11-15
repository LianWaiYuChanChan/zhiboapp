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

    private String secretKey;

    public KingSoftServiceImpl(String secretKey) {
        this.secretKey = secretKey;
    }

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

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    @Override
    public String computeSignature(Long expire, PushStreamResource pushStreamResource) throws Exception {
        //GET\n1436976000\nnonce=4e1f2519c626cbfbab1520c255830c26&vdoid=12345
        String stringToSign = "GET\n" + expire + "\nnonce=" +
                pushStreamResource.getNonce() + "&vdoid=" + pushStreamResource.getVdoid();
        return calculateRFC2104HMAC(secretKey, stringToSign);
    }
}
