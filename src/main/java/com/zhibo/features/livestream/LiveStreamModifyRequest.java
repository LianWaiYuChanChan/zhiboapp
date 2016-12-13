package com.zhibo.features.livestream;

/**
 * Created by jichao on 2016/12/13.
 */
public class LiveStreamModifyRequest {


    public LiveStreamStatusEnum getStatus() {
        return status;
    }

    public void setStatus(LiveStreamStatusEnum status) {
        this.status = status;
    }

    private LiveStreamStatusEnum status;

}
