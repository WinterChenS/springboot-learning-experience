package com.winterchen.webservice.client.webservice.user;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 3.2.5
 * 2021-12-16T17:38:44.102+08:00
 * Generated source version: 3.2.5
 *
 */
@WebService(targetNamespace = "http://user.winterchen.com/webservice", name = "userInfoType")
@XmlSeeAlso({ObjectFactory.class})
public interface UserInfoType {

    @WebMethod
    @RequestWrapper(localName = "findUserById", targetNamespace = "http://user.winterchen.com/webservice", className = "com.winterchen.webservice.client.webservice.user.FindUserById")
    @ResponseWrapper(localName = "findUserByIdResponse", targetNamespace = "http://user.winterchen.com/webservice", className = "com.winterchen.webservice.client.webservice.user.FindUserByIdResponse")
    @WebResult(name = "return", targetNamespace = "")
    public UserInfoDTO findUserById(
        @WebParam(name = "arg0", targetNamespace = "")
        Long arg0
    );

    @WebMethod
    @RequestWrapper(localName = "pageUserInfo", targetNamespace = "http://user.winterchen.com/webservice", className = "com.winterchen.webservice.client.webservice.user.PageUserInfo")
    @ResponseWrapper(localName = "pageUserInfoResponse", targetNamespace = "http://user.winterchen.com/webservice", className = "com.winterchen.webservice.client.webservice.user.PageUserInfoResponse")
    @WebResult(name = "return", targetNamespace = "")
    public CommonPage pageUserInfo(
        @WebParam(name = "arg0", targetNamespace = "")
                BasePage arg0
    );
}
