package com.zhibo.kingsoft;

/**
 * Created by jichao on 2016/11/15.
 */
public class PushStreamResource {

    private String nonce;
    private String vdoid;

    public String getNonce() {
        return nonce;
    }

    public void setNonce(String nonce) {
        this.nonce = nonce;
    }

    public String getVdoid() {
        return vdoid;
    }

    public void setVdoid(String vdoid) {
        this.vdoid = vdoid;
    }

    public String getResourceStr() {
        //"nonce=4e1f2519c626cbfbab1520c255830c26&vdoid=12345"
        return "nonce=" + this.nonce + "&vdoid=" + this.vdoid;
    }

    @Override
    public String toString() {
        return "";
    }
}
