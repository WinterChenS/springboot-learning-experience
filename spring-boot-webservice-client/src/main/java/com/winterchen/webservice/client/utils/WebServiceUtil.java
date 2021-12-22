package com.winterchen.webservice.client.utils;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.cxf.transports.http.configuration.HTTPClientPolicy;

/**
 * @author winterchen.com
 * @version 1.0
 * @date 2021/12/16 14:20
 * @description 动态
 **/
public class WebServiceUtil {

    public static Client createClient(String url) {
        // 创建动态客户端信息
        JaxWsDynamicClientFactory factory = JaxWsDynamicClientFactory.newInstance();
        // 客户端链接
        Client client = factory.createClient(url);
        // 解决连接文件数大的问题
        HTTPConduit http = (HTTPConduit)client.getConduit();
        //策略
        HTTPClientPolicy httpClientPolicy = new HTTPClientPolicy();
        //连接超时(1分钟)
        httpClientPolicy.setConnectionTimeout(1 * 60000);
        httpClientPolicy.setAllowChunking(false);
        //响应超时(1分钟)
        httpClientPolicy.setReceiveTimeout(1 * 60000);
        http.setClient(httpClientPolicy);
        return client;
    }

    public WebServiceUtil() {
    }

    /**
     * webservice请求
     * @param methodName 请求方法名
     * @param pram       请求参数
     * @return
     */
    public static Object[] webServiceRequest(String requestUrl, String methodName, Object... pram) {
        Client client = createClient(requestUrl);
        Object[] result = null;
        try{
            result = client.invoke(methodName, pram);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            client.destroy();
        }
        return result;
    }

}