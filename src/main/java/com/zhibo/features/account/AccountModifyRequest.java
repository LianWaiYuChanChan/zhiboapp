package com.zhibo.features.account;


/**
 * Created by jichao on 2016/10/27.
 */
public class AccountModifyRequest {
    private String phoneNumber;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
