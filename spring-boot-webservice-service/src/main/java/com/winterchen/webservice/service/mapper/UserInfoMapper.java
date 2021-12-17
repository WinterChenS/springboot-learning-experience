package com.winterchen.webservice.service.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.winterchen.webservice.service.entity.UserInfoEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserInfoMapper extends BaseMapper<UserInfoEntity> {


}