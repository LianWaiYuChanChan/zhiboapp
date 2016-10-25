package com.zhibo.features.account;

/**
 * Created by jichao on 2016/10/24.
 */
public class AccountCreateRequest {
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

    private String name;
    private String phoneNumber;

    public Account toAccount() {
        final Account account = new Account();
        account.setName(this.name);
        account.setPhoneNumber(this.phoneNumber);
        return account;
    }


}
