package com.zhibo.features.livestream;

import com.zhibo.infra.InternalErrorException;
import com.zhibo.infra.Logger;
import com.zhibo.infra.ZhiBoBaseException;
import com.zhibo.kingsoft.KingSoftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;

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

    @Override
    public LiveStream pushStream(final LiveStreamPushRequest liveStreamPushRequest) throws ZhiBoBaseException {
        //Interact with KingSoft cloud and get related into and persist them.

        String name = liveStreamPushRequest.getName();
        Long expire = Instant.now().getEpochSecond() + 5 * 60 * 60; //5 hours timeout
        String nonce = "TODO";
        String vdoid = "123";
        String accessKey = "aceesKeyValueFromWhere";
        String pushUrl = null;
        try {
            pushUrl = kingSoftService.constructPushUrl(name, expire, nonce, vdoid, accessKey);
        } catch (Exception e) {
            Logger.error("Internal Error in calling kingSoftService.", e);
            throw new InternalErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error.");
        }
        String pullUrl = "rtmp://" + name + ".rtmplive.ks-cdn.com/live/stream";
        LiveStream liveStream = new LiveStream();
        liveStream.setPushUrl(pushUrl);
        liveStream.setPullUrl(pullUrl);
        liveStream.setName(name);
        return liveStreamDao.create(liveStream);
    }
}
