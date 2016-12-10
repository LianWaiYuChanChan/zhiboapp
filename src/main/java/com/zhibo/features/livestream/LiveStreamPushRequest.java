package com.zhibo.features.livestream;

import com.zhibo.features.account.Account;

/**
 * Created by jichao on 2016/11/14.
 */
public class LiveStreamPushRequest {

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    private Account account;

    public Boolean getPublic() {
        return isPublic;
    }

    public void setPublic(Boolean aPublic) {
        isPublic = aPublic;
    }

    private Boolean isPublic;
}
