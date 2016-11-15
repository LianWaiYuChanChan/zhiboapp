package com.zhibo.features.livestream;

import com.zhibo.infra.RequestData;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by jichao on 2016/11/14.
 */
@Repository
public class LiveStreamDaoImpl implements LiveStreamDao{

    @Override
    public LiveStream create(LiveStream liveStream) {
        return null;
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
