package com.winterchen.webservice.service.config;

import com.winterchen.webservice.service.service.AuthorService;
import com.winterchen.webservice.service.service.UserInfoService;
import com.winterchen.webservice.service.service.impl.AuthorServiceImpl;
import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.ws.Endpoint;

/**
 * @author CENTURY
 * @version 1.0
 * @date 2021/12/16 10:24
 * @description TODO
 **/
@Configuration
public class CxfWebServiceConfig {

    @Autowired
    private Bus bus;

    @Autowired
    private AuthorService authorService;

    @Autowired
    private UserInfoService userInfoService;

    /**
     * 申明业务处理类 当然也可以直接 在实现类上标注 @Service
     * @author oKong
     */
    @Bean
    public AuthorService authorService() {
        return new AuthorServiceImpl();
    }


    /*
     * 发布endpoint
     */
    @Bean
    public Endpoint endpoint() {
        EndpointImpl endpoint = new EndpointImpl(bus, authorService);
        endpoint.publish("/author");//发布地址
        return endpoint;
    }

    @Bean
    public Endpoint userEndpoint() {
        EndpointImpl endpoint = new EndpointImpl(bus, userInfoService);
        endpoint.publish("/user");//发布地址
        return endpoint;
    }

}