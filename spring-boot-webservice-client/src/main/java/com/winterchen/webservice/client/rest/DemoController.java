package com.winterchen.webservice.client.rest;

import com.winterchen.webservice.client.utils.WebServiceUtil;
import com.winterchen.webservice.client.webservice.AuthorDto;
import com.winterchen.webservice.client.webservice.AuthorPortType;
import org.apache.cxf.endpoint.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author CENTURY
 * @version 1.0
 * @date 2021/12/16 13:50
 * @description TODO
 **/
@RestController
@RequestMapping("/cxf")
public class DemoController {

    @Autowired
    Client client;

    @Autowired
    @Qualifier("cxfProxy")
    AuthorPortType authorPort;

    @GetMapping("/getauthorstring")
    public String getAuthorString(String authorName) {
        return authorPort.getAuthorString(authorName);
    }

    @GetMapping("/getauthor")
    public AuthorDto getAuthor(String authorName) {
        return authorPort.getAuthorByName(authorName);
    }

    @GetMapping("/getauthorlist")
    public List<AuthorDto> getAuthorList() {
        return authorPort.getAuthorList();
    }

    @GetMapping("/dynamic/{operation}")
    public Object getAuthorStringByDynamic(@PathVariable("operation")String operationName, String authorName) throws Exception {
        //这里就简单的判断了
        Object[] objects = null;
//        client.getEndpoint().getBinding().getBindingInfo().getOperations()
        if ("getAuthorList".equalsIgnoreCase(operationName)) {
            objects = client.invoke(operationName);
        } else if ("getAuthorString".equalsIgnoreCase(operationName)) {
            objects = client.invoke(operationName, authorName);
        } else if ("getAuthorByName".equalsIgnoreCase(operationName)) {
            objects = client.invoke(operationName, authorName);
        } else {
            throw new RuntimeException("无效的调用方法");
        }
        return objects != null && objects.length > 0 ? objects[0] : "返回异常";
    }

    @GetMapping("/client")
    public String createClientTest(
            @RequestParam(name = "url")
            String url
    ) {
        Client client = WebServiceUtil.createClient(url);
        return "success";
    }

}