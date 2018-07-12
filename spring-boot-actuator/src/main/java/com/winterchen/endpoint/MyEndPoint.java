package com.winterchen.endpoint;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;

import java.util.HashMap;
import java.util.Map;

/**
 * * <p>@Endpoint 是构建 rest 的唯一路径 </p>
 * 不同请求的操作，调用时缺少必需参数，或者使用无法转换为所需类型的参数，则不会调用操作方法，响应状态将为400（错误请求）
 * <P>@ReadOperation = GET 响应状态为 200 如果没有返回值响应 404（资源未找到） </P>
 * <P>@WriteOperation = POST 响应状态为 200 如果没有返回值响应 204（无响应内容） </P>
 * <P>@DeleteOperation = DELETE 响应状态为 200 如果没有返回值响应 204（无响应内容） </P>
 *
 * Created by Donghua.Chen on 2018/7/12.
 */
@Endpoint(id = "luis")
public class MyEndPoint {

    @ReadOperation
    public Map<String, String> hello() {
        Map<String, String> result = new HashMap<>();
        result.put("author", "Luis");
        result.put("age", "25");
        result.put("email", "1085143002@qq.com");
        return result;
    }
}