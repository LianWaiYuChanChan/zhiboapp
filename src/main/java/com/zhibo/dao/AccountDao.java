package com.zhibo.dao;

import com.zhibo.domain.Account;

/**
 * Created by jichao on 2016/10/23.
 */
public interface AccountDao {
    Account getAccountById(final String id);
}
