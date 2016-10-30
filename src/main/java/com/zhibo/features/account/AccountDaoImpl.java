package com.zhibo.features.account;

import com.zhibo.infra.InternalErrorException;
import com.zhibo.infra.Logger;
import com.zhibo.infra.ResourceNotFoundException;
import com.zhibo.infra.ZhiBoBaseException;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by jichao on 2016/10/23.
 */
@Repository
public class AccountDaoImpl implements AccountDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Account getAccountById(String id) throws ZhiBoBaseException {
        Session session = sessionFactory.getCurrentSession();
        try {
            Long.parseLong(id);
        } catch (NumberFormatException e) {
            Logger.warn("Invalid account id: " + id + ", as it is not a integer.");
            Logger.error("Invalid account id. ", e);
            throw new ResourceNotFoundException("account", id);
        }

        Query query = session.createQuery("from Account account where id = '" + id + "'");
        List list = query.list();
        if (list.size() == 1) {
            return (Account) list.get(0);
        } else if (list.size() > 1) {
            throw new InternalErrorException("Get more than one rows for id: " + id);
        } else {
            throw new ResourceNotFoundException("account", id);
        }
    }

    @Override
    public Account createAccount(Account account) throws ZhiBoBaseException {
        Session session = sessionFactory.getCurrentSession();
        try {
            session.save(account);
        } catch (HibernateException e) {
            Logger.error("Internal Error in createAccount.", e);
            throw new InternalErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error.");
        }
        return account;
    }

    @Override
    public void deleteAccount(String id) throws ZhiBoBaseException {
        Session session = sessionFactory.getCurrentSession();

        Account account = getAccountById(id);
        try {
            session.delete(account);
        } catch (Exception e) {
            Logger.error("Internal Error in deleteAccount.", e);
            throw new InternalErrorException();
        }
    }

    @Override
    public void modifyAccount(String id, AccountModifyRequest accountModifyRequest) throws ZhiBoBaseException {
        Session session = sessionFactory.getCurrentSession();
        Account account = getAccountById(id);
        if (accountModifyRequest.getName() != null) {
            account.setName(accountModifyRequest.getName());
        }
        if (accountModifyRequest.getPhoneNumber() != null) {
            account.setPhoneNumber(accountModifyRequest.getPhoneNumber());
        }
        try {
            session.update(account);
        } catch (Exception e) {
            Logger.error("Internal Error in modifyAccount.", e);
            throw new InternalErrorException();
        }
    }

    @Override
    public List<Account> getAccounts() throws ZhiBoBaseException {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Account account");
        List list = query.list();
        return (List<Account>) list;
    }
}
