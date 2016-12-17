package com.zhibo.features.livestream;

import com.zhibo.infra.*;
import com.zhibo.kingsoft.KingSoftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.time.Instant;
import java.util.List;

/**
 * Created by jichao on 2016/11/14.
 */
@Service
@Transactional
public class LiveStreamServiceImpl implements LiveStreamService {
    @Autowired
    private LiveStreamDao liveStreamDao;

    @Autowired
    private KingSoftService kingSoftService;

    private SecureRandom random = new SecureRandom();

    /**
     * Temp solution.
     * @return
     */
    private String generateNonce(){
        return new BigInteger(130, random).toString(32);
    }

    @Override
    public LiveStream pushStream(final LiveStreamPushRequest liveStreamPushRequest) throws ZhiBoBaseException {
        String name = liveStreamPushRequest.getName();
        Long expire = Instant.now().getEpochSecond() + 5 * 60 * 60; //5 hours timeout
        String nonce = generateNonce();
        String vdoid = "123";
        String pushUrl = null;
        try {
            pushUrl = kingSoftService.constructPushUrl(name, expire, nonce, vdoid);
        } catch (Exception e) {
            Logger.error("Internal Error in calling kingSoftService.", e);
            throw new InternalErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error.");
        }
        String pullUrl = "rtmp://" + name + ".rtmplive.ks-cdn.com/live/stream";
        LiveStream liveStream = new LiveStream();
        liveStream.setPushUrl(pushUrl);
        liveStream.setPullUrl(pullUrl);
        liveStream.setName(name);
        liveStream.setPublic(liveStreamPushRequest.getPublic());
        liveStream.setStatus(LiveStreamStatusEnum.INITIALIZED);
        liveStream.setHost(liveStreamPushRequest.getAccount());
        return liveStreamDao.create(liveStream);
    }

    @Override
    public List<LiveStream> getAll(RequestData requestData) throws ZhiBoBaseException {
        return liveStreamDao.getAll(requestData);
    }

    @Override
    public LiveStream getById(String idStr) throws ZhiBoBaseException {
        return liveStreamDao.getById(idStr);
    }

    @Override
    public void deleteById(String id) throws ZhiBoBaseException {
        liveStreamDao.deleteById(id);
    }

    @Override
    public void modify(String id, LiveStreamModifyRequest modifyRequest) throws ZhiBoBaseException {
        liveStreamDao.modify(id, modifyRequest);
    }
}
