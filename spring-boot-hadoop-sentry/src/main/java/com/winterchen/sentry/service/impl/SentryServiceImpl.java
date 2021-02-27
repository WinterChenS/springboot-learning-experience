package com.winterchen.sentry.service.impl;

import com.winterchen.sentry.service.SentryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.sentry.api.service.thrift.SentryPolicyServiceClient;
import org.apache.sentry.api.service.thrift.TSentryRole;
import org.apache.sentry.core.common.exception.SentryUserException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Set;

/**
 * @author winterchen
 * @version 1.0
 * @date 2021/1/22 11:06 上午
 * @description TODO
 **/
@Slf4j
@Service
@RequiredArgsConstructor
public class SentryServiceImpl implements SentryService {


    private final SentryPolicyServiceClient sentryPolicyServiceClient;

    @Override
    public Set<TSentryRole> listAllRoles(String username) {
        try {
            return sentryPolicyServiceClient.listAllRoles(username);
        } catch (SentryUserException e) {
            log.error(e.getReason(), e);
        }
        return Collections.emptySet();
    }

    @Override
    public void createRole(String username, String role) {
        try {
            sentryPolicyServiceClient.createRole(username, role);
        } catch (SentryUserException e) {
            log.error(e.getReason(), e);
        }
    }
}
