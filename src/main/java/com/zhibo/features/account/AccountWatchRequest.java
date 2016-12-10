package com.zhibo.features.account;

import com.zhibo.features.livestream.LiveStream;

/**
 * Created by jichao on 2016/12/9.
 */
public class AccountWatchRequest {

    public LiveStream getLiveStream() {
        return liveStream;
    }

    public void setLiveStream(LiveStream liveStream) {
        this.liveStream = liveStream;
    }

    private LiveStream liveStream;

}
