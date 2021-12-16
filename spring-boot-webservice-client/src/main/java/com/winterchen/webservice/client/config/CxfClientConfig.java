package com.winterchen.webservice.client.config;

import com.winterchen.webservice.client.constants.WsConst;
import com.winterchen.webservice.client.webservice.AuthorPortType;
import com.winterchen.webservice.client.webservice.AuthorService;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author CENTURY
 * @version 1.0
 * @date 2021/12/16 13:42
 * @description TODO
 **/
@Configuration
public class CxfClientConfig {


    /**
     *  以接口代理方式进行调用 AuthorPortType接口
     */
    @Bean("cxfProxy")
    public AuthorPortType createAuthorPortTypeProxy() {
        JaxWsProxyFactoryBean jaxWsProxyFactoryBean = new JaxWsProxyFactoryBean();
        jaxWsProxyFactoryBean.setServiceClass(AuthorPortType.class);
        jaxWsProxyFactoryBean.setAddress(WsConst.SERVICE_ADDRESS);//服务地址：http://127.0.0.1:8080/ws/autho

        return (AuthorPortType) jaxWsProxyFactoryBean.create();
    }

    /*
     * 采用动态工厂方式 不需要指定服务接口
     */
    @Bean
    public Client createDynamicClient() {
        JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
        Client client = dcf.createClient(WsConst.SERVICE_ADDRESS);
        return client;
    }

    /*
     * 直接调用
     */
    @Bean("jdkProxy")
    public AuthorPortType createJdkService() {
        AuthorService authorService = new AuthorService();
        return authorService.getAuthorPortName();
    }
}