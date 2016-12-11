package com.zhibo.features.livestream;

import com.zhibo.infra.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    public Object getAll(
            HttpServletRequest request,
            HttpServletResponse response,
            RequestData requestData
    ) throws ZhiBoBaseException {
        try {
            return ResourceCollection.create(liveStreamService.getAll(requestData));
        } catch (ZhiBoBaseException e) {
            response.setStatus(e.getStatusCode().value());
            return new ErrorResponse(e);
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/api/livestream/{id}")
    public ResponseObject getById(
            HttpServletRequest request,
            HttpServletResponse response,
            @PathVariable("id") String idStr
    ) {
        try {
            return liveStreamService.getById(idStr);
        } catch (ZhiBoBaseException e) {
            response.setStatus(e.getStatusCode().value());
            return new ErrorResponse(e);
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/api/livestream")
    @ResponseBody
    public Object create(
            HttpServletRequest request,
            HttpServletResponse response,
            @RequestBody LiveStreamPushRequest pushRequest) throws ZhiBoBaseException {
        return liveStreamService.pushStream(pushRequest);
    }

    @RequestMapping(method = RequestMethod.DELETE
            , value = "/api/livestream/{id}"
            , consumes = "application/json")
    @ResponseBody
    public ResponseObject deleteById(
            HttpServletRequest request,
            HttpServletResponse response,
            @PathVariable("id") String id) {
        try {
            liveStreamService.deleteById(id);
            response.setStatus(HttpStatus.NO_CONTENT.value());
            return null;
        } catch (ZhiBoBaseException e) {
            response.setStatus(e.getStatusCode().value());
            return new ErrorResponse(e);
        }
    }

    @RequestMapping(method = RequestMethod.POST
            , value = "/api/livestream/{id}/sendheartbeat"
            , consumes = "application/json")
    @ResponseBody
    public ResponseObject sendHeartbeat(
            HttpServletRequest request,
            HttpServletResponse response,
            @PathVariable("id") String id) {
        try {
            liveStreamService.sendHeartbeat(id);
            response.setStatus(HttpStatus.NO_CONTENT.value());
            return null;
        } catch (ZhiBoBaseException e) {
            response.setStatus(e.getStatusCode().value());
            return new ErrorResponse(e);
        }
    }

}
