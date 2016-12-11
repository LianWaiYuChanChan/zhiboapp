package com.zhibo.features.livestream;

import com.zhibo.infra.InternalErrorException;
import com.zhibo.infra.RequestData;
import com.zhibo.infra.ResponseObject;
import com.zhibo.infra.ZhiBoBaseException;

import java.util.List;

/**
 * Created by jichao on 2016/11/14.
 */
public interface LiveStreamDao {

    /**
     * Create live stream according to the live stream instance.
     *
     * @param liveStream
     * @return
     */
    LiveStream create(final LiveStream liveStream) throws ZhiBoBaseException;

    /**
     * Modify live stream according to the non-id properties of liveStream.
     *
     * @param liveStream
     */
    void modify(final LiveStream liveStream);

    /**
     * Get live stream according to the id property of liveStream.
     *
     * @param liveStream
     * @return
     */
    LiveStream get(final LiveStream liveStream, final RequestData requestData);

    /**
     * Get live streams according to parameters in RequestData
     *
     * @param requestData
     * @return
     */
    List<LiveStream> getAll(final RequestData requestData);

    LiveStream getById(String idStr) throws ZhiBoBaseException;

    void deleteById(String id) throws ZhiBoBaseException;

    void updateStatusAsOk(String id) throws ZhiBoBaseException;

    void close(String id) throws ZhiBoBaseException;
}
