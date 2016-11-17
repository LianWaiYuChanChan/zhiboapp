package com.zhibo.features.livestream;

import com.zhibo.infra.InternalErrorException;
import com.zhibo.infra.Logger;
import com.zhibo.infra.RequestData;
import com.zhibo.infra.ZhiBoBaseException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
    public void delete(LiveStream liveStream) {

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
        return null;
    }
}
