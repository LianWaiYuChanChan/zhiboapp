package com.zhibo.features.account;

import com.zhibo.infra.ResponseObject;
import com.zhibo.infra.ZhiBoBaseException;

import java.util.List;

/**
 * Created by jichao on 2016/10/23.
 */
public interface AccountService {
    Account getAccountById(final String id) throws  ZhiBoBaseException;

    Account createAccount(final AccountCreateRequest accountCreateRequest) throws ZhiBoBaseException;

    void deleteAccount(String id) throws  ZhiBoBaseException;

    void modifyAccount(String id, AccountModifyRequest accountModifyRequest) throws ZhiBoBaseException;

    List<Account> getAccounts() throws ZhiBoBaseException;
}
