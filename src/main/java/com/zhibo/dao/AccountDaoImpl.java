package com.zhibo.dao;

import com.zhibo.domain.Account;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
}
