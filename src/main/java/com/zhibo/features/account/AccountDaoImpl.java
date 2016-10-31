package com.zhibo.features.account;

import com.github.tennaito.rsql.jpa.JpaCriteriaQueryVisitor;
import com.zhibo.infra.*;
import cz.jirutka.rsql.parser.RSQLParser;
import cz.jirutka.rsql.parser.ast.Node;
import cz.jirutka.rsql.parser.ast.RSQLVisitor;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

/**
 * Created by jichao on 2016/10/23.
 */
@Repository
public class AccountDaoImpl implements AccountDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Account getAccountById(String id) throws ZhiBoBaseException {
        Long longTypeId;
        try {
            longTypeId = Long.parseLong(id);
        } catch (NumberFormatException e) {
            Logger.warn("Invalid account id: " + id + ", as it is not a integer.");
            Logger.error("Invalid account id. ", e);
            throw new ResourceNotFoundException("account", id);
        }
        return entityManager.find(Account.class, longTypeId);
        /*
        Query query = session.createQuery("from Account account where id = '" + id + "'");
        List list = query.list();
        if (list.size() == 1) {
            return (Account) list.get(0);
        } else if (list.size() > 1) {
            throw new InternalErrorException("Get more than one rows for id: " + id);
        } else {
            throw new ResourceNotFoundException("account", id);
        }*/
    }

    @Override
    public Account createAccount(Account account) throws ZhiBoBaseException {
        /*
        Session session = sessionFactory.getCurrentSession();
        try {
            session.save(account);
        } catch (HibernateException e) {
            Logger.error("Internal Error in createAccount.", e);
            throw new InternalErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error.");
        }
        return account;
        */
        return null;
    }

    @Override
    public void deleteAccount(String id) throws ZhiBoBaseException {
        /*
        Session session = sessionFactory.getCurrentSession();

        Account account = getAccountById(id);
        try {
            session.delete(account);
        } catch (Exception e) {
            Logger.error("Internal Error in deleteAccount.", e);
            throw new InternalErrorException();
        }
        */
    }

    @Override
    public void modifyAccount(String id, AccountModifyRequest accountModifyRequest) throws ZhiBoBaseException {
        /*
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
        */
    }

    @Override
    public List<Account> getAccounts(final RequestData requestData) throws ZhiBoBaseException {
        // We will need a JPA EntityManager
        List<Account> accounts;
        if (requestData.getFilter() != null) {

            // Create the JPA Visitor
            RSQLVisitor<CriteriaQuery<Account>, EntityManager> visitor = new JpaCriteriaQueryVisitor<Account>();

            // Parse a RSQL into a Node
            Node rootNode = new RSQLParser().parse(requestData.getFilter());

            // Visit the node to retrieve CriteriaQuery
            CriteriaQuery<Account> query = rootNode.accept(visitor, entityManager);

            // Do all sort of operations you want with the criteria query
            //query.orderBy(...);

            // Execute and get results
            accounts = entityManager.createQuery(query).getResultList();
        } else {
            accounts = entityManager.createQuery("SELECT a FROM Account a", Account.class).getResultList();
        }
        return accounts;

    }
}
