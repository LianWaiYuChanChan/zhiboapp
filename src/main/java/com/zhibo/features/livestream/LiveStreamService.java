package com.zhibo.features.livestream;

/**
 * Created by jichao on 2016/11/14.
 */
public interface LiveStreamService {
    LiveStream pushStream(final LiveStreamPushRequest liveStreamPushRequest);
}
