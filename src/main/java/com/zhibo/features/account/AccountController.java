package com.zhibo.features.account;

import com.zhibo.infra.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by jichao on 2016/10/23.
 */

@Controller
public class AccountController {
    private static String CLASS_NAME = "AccountController";

    @Autowired
    private AccountService accountService;

    @RequestMapping(method = RequestMethod.GET, value = "/api/account")
    @ResponseBody
    public Object getAccounts(
            HttpServletRequest request,
            HttpServletResponse response,
            RequestData requestData
    ) {
        final String METHOD_NAME = CLASS_NAME + ".getAccounts -- ";
        Logger.trace(METHOD_NAME + "Enter.");
        try {
            return ResourceCollection.create(accountService.getAccounts(requestData));
        } catch (ZhiBoBaseException e) {
            response.setStatus(e.getStatusCode().value());
            return new ErrorResponse(e);
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/api/account/{id}")
    public ResponseObject getAccount(
            HttpServletRequest request,
            HttpServletResponse response,
            @PathVariable("id") String idStr
    ) {
        try {
            return accountService.getAccountById(idStr);
        } catch (ZhiBoBaseException e) {
            response.setStatus(e.getStatusCode().value());
            return new ErrorResponse(e);
        }
    }

    @RequestMapping(
            method = RequestMethod.POST
            , value = "/api/account"
            , consumes = "application/json"
    )
    @ResponseBody
    public ResponseObject createAccount(
            HttpServletRequest request,
            HttpServletResponse response,
            @RequestBody AccountCreateRequest createRequest) {
        try {
            return accountService.createAccount(createRequest);
        } catch (ZhiBoBaseException e) {
            response.setStatus(e.getStatusCode().value());
            return new ErrorResponse(e);
        }
    }

    @RequestMapping(method = RequestMethod.DELETE
            , value = "/api/account/{id}"
            , consumes = "application/json")
    @ResponseBody
    public ResponseObject deleteAccount(
            HttpServletRequest request,
            HttpServletResponse response,
            @PathVariable("id") String id) {
        try {
            accountService.deleteAccount(id);
            response.setStatus(HttpStatus.NO_CONTENT.value());
            return null;
        } catch (ZhiBoBaseException e) {
            response.setStatus(e.getStatusCode().value());
            return new ErrorResponse(e);
        }
    }

    @RequestMapping(method = RequestMethod.POST
            , value = "/api/account/{id}"
            , consumes = "application/json")
    @ResponseBody
    public ResponseObject modify(
            HttpServletRequest request,
            HttpServletResponse response,
            @PathVariable("id") String id,
            @RequestBody AccountModifyRequest accountModifyRequest) {
        try {
            accountService.modifyAccount(id, accountModifyRequest);
            response.setStatus(HttpStatus.NO_CONTENT.value());
            return null;
        } catch (ZhiBoBaseException e) {
            response.setStatus(e.getStatusCode().value());
            return new ErrorResponse(e);
        }
    }

}
