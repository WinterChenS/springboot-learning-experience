package com.winterchen.webservice.service.service.impl;

import com.winterchen.webservice.service.constants.WsConst;
import com.winterchen.webservice.service.dto.AuthorDto;
import com.winterchen.webservice.service.enums.Sex;
import com.winterchen.webservice.service.service.AuthorService;

import javax.jws.WebService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author CENTURY
 * @version 1.0
 * @date 2021/12/16 10:19
 * @description TODO
 **/
@WebService(
        targetNamespace = WsConst.NAMESPACE_URI, //wsdl命名空间
        name = "authorPortType",                 //portType名称 客户端生成代码时 为接口名称
        serviceName = "authorService",           //服务name名称
        portName = "authorPortName",             //port名称
        endpointInterface = "com.winterchen.webservice.service.service.AuthorService")
public class AuthorServiceImpl implements AuthorService {

    @Override
    public AuthorDto getAuthor(String name) {
        AuthorDto author = new AuthorDto();
        author.setBirthday("1990-01-23");
        author.setName("姓名：" + name);
        author.setSex(Sex.MALE);
        author.setHobby(Arrays.asList("电影","旅游"));
        author.setDescription("描述：一枚趔趄的猿。现在时间：" + new Date().getTime());
        return author;
    }

    @Override
    public List<AuthorDto> getAuthorList() {
        List<AuthorDto> resultList = new ArrayList<>();
        AuthorDto author = new AuthorDto();
        author.setBirthday("1990-01-23");
        author.setName("姓名：oKong");
        author.setSex(Sex.MALE);
        author.setHobby(Arrays.asList("电影","旅游"));
        author.setDescription("描述：一枚趔趄的猿。现在时间：" + new Date().getTime());
        resultList.add(author);
        resultList.add(author);
        return resultList;
    }

    @Override
    public String getAuthorString(String name) {
        AuthorDto author = getAuthor(name);
        return author.toString();
    }
}