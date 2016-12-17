package com.zhibo.kingsoft;

/**
 * Created by jichao on 2016/11/15.
 */
public interface KingSoftService {
    String constructPushUrl(String zhiboName, Long expire, String nonce, String vdoid) throws Exception;
}
