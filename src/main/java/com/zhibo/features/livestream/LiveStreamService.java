package com.zhibo.features.livestream;

import com.zhibo.infra.RequestData;
import com.zhibo.infra.ResponseObject;
import com.zhibo.infra.ZhiBoBaseException;

import java.util.List;

/**
 * Created by jichao on 2016/11/14.
 */
interface LiveStreamService {
    LiveStream pushStream(final LiveStreamPushRequest liveStreamPushRequest) throws ZhiBoBaseException;

    List<LiveStream> getAll(RequestData requestData) throws ZhiBoBaseException;

    LiveStream getById(String idStr) throws ZhiBoBaseException;

    void deleteById(String id) throws ZhiBoBaseException;

    void sendHeartbeat(String id) throws ZhiBoBaseException;
}
