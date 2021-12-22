package com.winterchen.webservice.client.rest;

import com.winterchen.webservice.client.webservice.user.BasePage;
import com.winterchen.webservice.client.webservice.user.CommonPage;
import com.winterchen.webservice.client.webservice.user.UserInfoDTO;
import com.winterchen.webservice.client.webservice.user.UserInfoType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import javax.jws.WebParam;

/**
 * @author winterchen.com
 * @version 1.0
 * @date 2021/12/17 9:56
 * @description TODO
 **/
@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    @Qualifier("userInfoType")
    private UserInfoType userInfoType;

    @GetMapping("/{id}")
    public UserInfoDTO findUserById(
        @PathVariable("id")
        Long id
    ){
        return userInfoType.findUserById(id);
    }

    @GetMapping("/list")
    public CommonPage pageUserInfo(
        @RequestParam(name = "pageNum", defaultValue = "1", required = false)
        Integer pageNum,
        @RequestParam(name = "pageSize", defaultValue = "10", required = false)
        Integer pageSize
    ) {
        BasePage basePage = new BasePage();
        basePage.setPageNum(pageNum);
        basePage.setPageSize(pageSize);
        return userInfoType.pageUserInfo(basePage);
    }

}