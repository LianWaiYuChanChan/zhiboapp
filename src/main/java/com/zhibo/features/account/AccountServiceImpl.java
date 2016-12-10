package com.zhibo.features.account;

import com.zhibo.infra.RequestData;
import com.zhibo.infra.ZhiBoBaseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by jichao on 2016/10/23.
 */
@Service
@Transactional
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountDao accountDao;

    @Override
    public Account getAccountById(String id) throws  ZhiBoBaseException {
        return accountDao.getAccountById(id);
    }

    @Override
    public Account createAccount(AccountCreateRequest accountCreateRequest) throws ZhiBoBaseException {
        return accountDao.createAccount(accountCreateRequest.toAccount());
    }

    @Override
    public void deleteAccount(String id) throws ZhiBoBaseException {
        accountDao.deleteAccount(id);
    }

    @Override
    public void modifyAccount(String id, AccountModifyRequest accountModifyRequest) throws ZhiBoBaseException {
        accountDao.modifyAccount(id, accountModifyRequest);
    }

    @Override
    public List<Account> getAccounts(final RequestData requestData) throws ZhiBoBaseException {
        return accountDao.getAccounts(requestData);
    }

    @Override
    public void watchLiveStream(String id, AccountWatchRequest accountWatchRequest) throws ZhiBoBaseException {
        accountDao.watchLiveStream(id, accountWatchRequest.getLiveStream());
    }
}
