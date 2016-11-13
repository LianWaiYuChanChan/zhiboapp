package com.zhibo.features.account;

import com.github.tennaito.rsql.jpa.JpaCriteriaQueryVisitor;
import com.zhibo.infra.*;
import cz.jirutka.rsql.parser.RSQLParser;
import cz.jirutka.rsql.parser.ast.Node;
import cz.jirutka.rsql.parser.ast.RSQLVisitor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;
import java.util.ArrayList;
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
        Account account = entityManager.find(Account.class, longTypeId);
        if (account == null) {
            throw new ResourceNotFoundException("account", id);
        }
        return account;
    }

    @Override
    public Account createAccount(Account account) throws ZhiBoBaseException {
        try {
            entityManager.persist(account);
        } catch (Exception e) {
            Logger.error("Internal Error in createAccount.", e);
            throw new InternalErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error.");
        }
        return account;
    }

    @Override
    public void deleteAccount(String id) throws ZhiBoBaseException {
        Account account = getAccountById(id);
        try {
            entityManager.remove(account);
        } catch (Exception e) {
            Logger.error("Internal Error in deleteAccount.", e);
            throw new InternalErrorException();
        }
    }

    @Override
    public void modifyAccount(String id, AccountModifyRequest accountModifyRequest) throws ZhiBoBaseException {

        Account account = getAccountById(id);
        if (accountModifyRequest.getName() != null) {
            account.setName(accountModifyRequest.getName());
        }
        if (accountModifyRequest.getPhoneNumber() != null) {
            account.setPhoneNumber(accountModifyRequest.getPhoneNumber());
        }

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
            Logger.info("JPA Query is '" + query + "'");
            accounts = entityManager.createQuery(query).getResultList();
        } else {
           accounts = entityManager.createQuery("SELECT a FROM Account a", Account.class).getResultList();
        }
        return accounts;

    }
}
