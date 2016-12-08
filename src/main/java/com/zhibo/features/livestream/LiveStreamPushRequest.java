package com.zhibo.features.livestream;

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

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    private String name;
    private String accountId;//TODO: user reference type.

    public Boolean getPublic() {
        return isPublic;
    }

    public void setPublic(Boolean aPublic) {
        isPublic = aPublic;
    }

    private Boolean isPublic;
}
