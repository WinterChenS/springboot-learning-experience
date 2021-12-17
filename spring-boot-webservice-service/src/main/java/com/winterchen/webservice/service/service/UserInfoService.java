package com.winterchen.webservice.service.service;


import com.winterchen.webservice.service.constants.WsConst;
import com.winterchen.webservice.service.dto.BasePage;
import com.winterchen.webservice.service.dto.CommonPage;
import com.winterchen.webservice.service.dto.UserInfoDTO;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService(targetNamespace = WsConst.NAMESPACE_URI_USER ,name = "userInfoType")
public interface UserInfoService {


    @WebMethod
    UserInfoDTO findUserById(Long id);

    @WebMethod
    CommonPage<UserInfoDTO> pageUserInfo(BasePage page);

}
