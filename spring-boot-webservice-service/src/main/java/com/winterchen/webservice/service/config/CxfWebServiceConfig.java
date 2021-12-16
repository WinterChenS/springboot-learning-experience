package com.winterchen.webservice.service.config;

import com.winterchen.webservice.service.service.AuthorService;
import com.winterchen.webservice.service.service.impl.AuthorServiceImpl;
import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.jaxws.EndpointImpl;
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

    //这里需要注意  由于springmvc 的核心类 为DispatcherServlet
    //此处若不重命名此bean的话 原本的mvc就被覆盖了。可查看配置类：DispatcherServletAutoConfiguration
    //一种方法是修改方法名称 或者指定bean名称
    //这里需要注意 若beanName命名不是 cxfServletRegistration 时，会创建两个CXFServlet的。
    //具体可查看下自动配置类：Declaration org.apache.cxf.spring.boot.autoconfigure.CxfAutoConfiguration
    //也可以不设置此bean 直接通过配置项 cxf.path 来修改访问路径的
//    @Bean("cxfServletRegistration")
//    public ServletRegistrationBean dispatcherServlet() {
//        //注册servlet 拦截/ws 开头的请求 不设置 默认为：/services/*
//        return new ServletRegistrationBean(new CXFServlet());
//    }

    /**
     * 申明业务处理类 当然也可以直接 在实现类上标注 @Service
     * @author oKong
     */
    @Bean
    public AuthorService authorService() {
        return new AuthorServiceImpl();
    }

    /*
     * 非必要项
     */
    @Bean(name = Bus.DEFAULT_BUS_ID)
    public SpringBus springBus() {
        SpringBus springBus = new SpringBus();
        return springBus;
    }

    /*
     * 发布endpoint
     */
    @Bean
    public Endpoint endpoint(AuthorService authorService) {
        EndpointImpl endpoint = new EndpointImpl(springBus(), authorService);
        endpoint.publish("/author");//发布地址
        return endpoint;
    }

}