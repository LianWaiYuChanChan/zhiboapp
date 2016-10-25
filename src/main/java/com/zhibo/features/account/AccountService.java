package com.zhibo.features.account;

import com.zhibo.infra.ZhiBoBaseException;

/**
 * Created by jichao on 2016/10/23.
 */
public interface AccountService {
    Account getAccountById(final String id);

    Account createAccount(final AccountCreateRequest accountCreateRequest) throws ZhiBoBaseException;
}
