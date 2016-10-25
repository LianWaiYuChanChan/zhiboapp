package com.zhibo.features.account;

import com.zhibo.infra.ErrorResponse;
import com.zhibo.infra.ResponseObject;
import com.zhibo.infra.ZhiBoBaseException;
import org.hibernate.hql.internal.antlr.HqlSqlTokenTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by jichao on 2016/10/23.
 */

@Controller
public class AccountController {

    @Autowired
    private AccountService accountService;

    @RequestMapping(method = RequestMethod.GET, value = "/api/account/{id}")
    public ModelAndView getAccount(
            HttpServletRequest request,
            HttpServletResponse response,
            @PathVariable("id") String idStr
    ) throws Exception {

        Account account = accountService.getAccountById(idStr);
        if (account != null) {
            return new ModelAndView("defaultView", "account", account);
        } else {
            response.setStatus(HttpStatus.NOT_FOUND.value());
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(),
                    "Not find account : " + idStr);
            return new ModelAndView("defaultView", "error", errorResponse);
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
            return new ErrorResponse(e);
        }
    }

}