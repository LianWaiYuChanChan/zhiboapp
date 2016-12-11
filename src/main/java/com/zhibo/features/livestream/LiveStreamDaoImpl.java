package com.zhibo.features.livestream;

import com.github.tennaito.rsql.jpa.JpaCriteriaQueryVisitor;
import com.zhibo.features.account.Account;
import com.zhibo.infra.*;
import cz.jirutka.rsql.parser.RSQLParser;
import cz.jirutka.rsql.parser.ast.Node;
import cz.jirutka.rsql.parser.ast.RSQLVisitor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

/**
 * Created by jichao on 2016/11/14.
 */
@Repository
public class LiveStreamDaoImpl implements LiveStreamDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public LiveStream create(LiveStream liveStream) throws ZhiBoBaseException {
        try {
            entityManager.persist(liveStream);
        } catch (Exception e) {
            Logger.error("Internal Error in createAccount.", e);
            throw new InternalErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error.");
        }
        return liveStream;
    }

    @Override
    public void modify(LiveStream liveStream) {

    }

    @Override
    public LiveStream get(LiveStream liveStream, RequestData requestData) {
        return null;
    }

    @Override
    public List<LiveStream> getAll(RequestData requestData) {
        List<LiveStream> liveStreams;
        if (requestData.getFilter() != null) {
            RSQLVisitor<CriteriaQuery<LiveStream>, EntityManager> visitor = new JpaCriteriaQueryVisitor<>();
            Node rootNode = new RSQLParser().parse(requestData.getFilter());
            CriteriaQuery<LiveStream> query = rootNode.accept(visitor, entityManager);
            // Do all sort of operations you want with the criteria query
            //query.orderBy(...);
            // Execute and get results
            Logger.info("JPA Query is '" + query + "'");
            liveStreams = entityManager.createQuery(query).getResultList();
        } else {
            liveStreams = entityManager.createQuery("SELECT liveStream FROM LiveStream liveStream", LiveStream.class).getResultList();
        }
        return liveStreams;
    }

    @Override
    public LiveStream getById(String id) throws ZhiBoBaseException {
        Long longTypeId;
        try {
            longTypeId = Long.parseLong(id);
        } catch (NumberFormatException e) {
            Logger.warn("Invalid account id: " + id + ", as it is not a integer.");
            Logger.error("Invalid account id. ", e);
            throw new ResourceNotFoundException("account", id);
        }
        LiveStream liveStream = entityManager.find(LiveStream.class, longTypeId);
        if (liveStream == null) {
            throw new ResourceNotFoundException("account", id);
        }
        return liveStream;
    }

    @Override
    public void deleteById(String id) throws ZhiBoBaseException {
        LiveStream liveStream = getById(id);
        try {
            entityManager.remove(liveStream);
        } catch (Exception e) {
            Logger.error("Internal Error in delete live steam.", e);
            throw new InternalErrorException();
        }
    }

    @Override
    public void updateStatusAsOk(String id) throws ZhiBoBaseException {
        LiveStream liveStream = getById(id);
        liveStream.setStatus(LiveStreamStatusEnum.OK);
        try {
            entityManager.persist(liveStream);
        } catch (Exception e) {
            Logger.error("Internal Error in updateStatusAsOk live steam for id = ." + id, e);
            throw new InternalErrorException();
        }
    }
}
