package com.winterchen.webservice.service.service.impl;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.winterchen.webservice.service.constants.WsConst;
import com.winterchen.webservice.service.dto.BasePage;
import com.winterchen.webservice.service.dto.CommonPage;
import com.winterchen.webservice.service.dto.UserInfoDTO;
import com.winterchen.webservice.service.entity.UserInfoEntity;
import com.winterchen.webservice.service.mapper.UserInfoMapper;
import com.winterchen.webservice.service.service.UserInfoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.jws.WebService;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Service
@WebService(
        targetNamespace = WsConst.NAMESPACE_URI_USER, //wsdl命名空间
        name = "userInfoType",                 //portType名称 客户端生成代码时 为接口名称
        serviceName = "userInfoService",           //服务name名称
        portName = "userInfoName",             //port名称
        endpointInterface = "com.winterchen.webservice.service.service.UserInfoService")
public class UserInfoServiceImpl  implements UserInfoService {


    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public UserInfoDTO findUserById(Long id) {
        return toConvertToDto(userInfoMapper.selectById(id));
    }

    @Override
    public CommonPage<UserInfoDTO> pageUserInfo(BasePage page) {
        Page<UserInfoEntity> page1 = new Page<>(page.getPageNum(), page.getPageSize());
        IPage<UserInfoEntity> dataPage = userInfoMapper.selectPage(page1, null);
        return CommonPage.create(toConvertToDtoList(dataPage.getRecords()), dataPage.getTotal());
    }

    private UserInfoDTO toConvertToDto(UserInfoEntity entity) {
        if (entity == null) return null;
        UserInfoDTO result = new UserInfoDTO();
        BeanUtils.copyProperties(entity, result);
        return result;
    }

    private List<UserInfoDTO> toConvertToDtoList(List<UserInfoEntity> entities) {
        if (CollectionUtils.isEmpty(entities)) return Collections.emptyList();
        List<UserInfoDTO> results = new ArrayList<>(entities.size());
        for (UserInfoEntity entity : entities) {
            results.add(toConvertToDto(entity));
        }
        return results;
    }
}
