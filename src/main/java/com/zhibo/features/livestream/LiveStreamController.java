package com.zhibo.features.livestream;

import com.zhibo.infra.RequestData;
import com.zhibo.infra.ZhiBoBaseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by jichao on 2016/11/14.
 */
@Controller
public class LiveStreamController {

    @Autowired
    private LiveStreamService liveStreamService;

    @RequestMapping(method = RequestMethod.GET, value = "/api/livestream")
    @ResponseBody
    public Object getLiveStreams(
            HttpServletRequest request,
            HttpServletResponse response,
            RequestData requestData
    ) {
        return null;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/api/livestream")
    @ResponseBody
    public Object pushStream(
            HttpServletRequest request,
            HttpServletResponse response,
            @RequestBody LiveStreamPushRequest pushRequest) throws ZhiBoBaseException {
        return liveStreamService.pushStream(pushRequest);
    }

}
