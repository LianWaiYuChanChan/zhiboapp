package com.zhibo.features.livestream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by jichao on 2016/11/14.
 */
@Service
public class LiveStreamServiceImpl implements LiveStreamService{
    @Autowired
    private LiveStreamDao liveStreamDao;

    @Override
    public LiveStream pushStream(final LiveStreamPushRequest liveStreamPushRequest) {
        //Interact with KingSoft cloud and get related into and persist them.
        return null;
    }
}
