package com.zhibo.web.controller;

import com.zhibo.domain.Account;
import com.zhibo.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by jichao on 2016/10/23.
 */

@Controller
public class ZhiBoController {

    @Autowired
    private AccountService accountService;

    @RequestMapping(method = RequestMethod.GET, value = "/api/account/{id}")
    public ModelAndView getAccount(
            HttpServletRequest request,
            HttpServletResponse response,
            @PathVariable("id") String idStr
            ) throws Exception {

        Account account = accountService.getAccountById(idStr);
        if(account != null){
            return new ModelAndView("defaultView", "account",account);
        }else{
            response.setStatus(HttpStatus.NOT_FOUND.value());
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setErrorMessage("Not found account: "+idStr);
            errorResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
            return new ModelAndView("defaultView", "error", errorResponse);
        }

        /*
        Account account = new Account();
        if("account_1".equals(idStr)) {
            account.setId("account_1");
            account.setName("Jack");
            account.setPhoneNumber("12330940888");
            return new ModelAndView("defaultView", "account",account);
        }else{
            response.setStatus(HttpStatus.NOT_FOUND.value());
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setErrorMessage("Not found account: "+idStr);
            errorResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
            return new ModelAndView("defaultView", "error", errorResponse);
        }
        */
    }
}
