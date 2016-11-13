package com.zhibo.infra;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * Created by jichao on 2016/11/3.
 */
public class RequestDataResovler implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        boolean ret = false;
        if (parameter.getParameterType().equals(RequestData.class)) {
            ret = true;
        }
        return ret;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        final RequestData requestData = new RequestData();
        String filter = webRequest.getParameter("filter");
        requestData.setFilter(filter);
        String fields = webRequest.getParameter("fields");
        requestData.setFilter(fields);
        return requestData;
    }
}
