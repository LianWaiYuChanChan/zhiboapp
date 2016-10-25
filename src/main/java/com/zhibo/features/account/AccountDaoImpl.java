package com.zhibo.features.account;

import com.zhibo.infra.InternalErrorException;
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
    public Account getAccountById(String id) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            Query query = session.createQuery("from Account account where id = '"+id+"'");

            List list = query.list();
            if(list.size() == 1){
                return (Account)list.get(0);
            }
        }finally {
            if (session != null) {
                session.close();
            }
        }
        return null;
    }

    @Override
    public Account createAccount(Account account) throws ZhiBoBaseException {
        Session session = sessionFactory.getCurrentSession();
        try {
            //session.beginTransaction(); @Transactional on Service should do that work.
            session.save(account);
            //session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
            throw new InternalErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error.");
        }
        return account;
    }

}
