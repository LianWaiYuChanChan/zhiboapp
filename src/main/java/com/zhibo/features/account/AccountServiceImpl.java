package com.zhibo.features.account;

import com.zhibo.infra.ZhiBoBaseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Created by jichao on 2016/10/23.
 */
@Service
@Transactional
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountDao accountDao;

    @Override
    public Account getAccountById(String id) {
        return accountDao.getAccountById(id);
    }

    @Override
    public Account createAccount(AccountCreateRequest accountCreateRequest) throws ZhiBoBaseException {
        return accountDao.createAccount(accountCreateRequest.toAccount());
    }
}
