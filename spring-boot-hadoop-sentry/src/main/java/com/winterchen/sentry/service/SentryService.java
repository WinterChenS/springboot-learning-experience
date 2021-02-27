package com.winterchen.sentry.service;


import org.apache.sentry.api.service.thrift.TSentryRole;

import java.util.Set;

/**
 * @author winterchen
 * @version 1.0
 * @date 2021/1/22 11:05 上午
 * @description sentry接口
 **/
public interface SentryService {

    Set<TSentryRole> listAllRoles(String username);

    void createRole(String username, String role);

}
