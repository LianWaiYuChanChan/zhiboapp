package com.zhibo.service;

import com.zhibo.dao.AccountDao;
import com.zhibo.domain.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by jichao on 2016/10/23.
 */
@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountDao accountDao;

    @Override
    public Account getAccountById(String id) {
        return accountDao.getAccountById(id);
    }
}
