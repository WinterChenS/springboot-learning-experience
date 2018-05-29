package com.winterchen.dubbo;

import com.winterchen.domain.User;

/**
 * Created by Donghua.Chen on 2018/5/29.
 */
public interface UserDubboService {

    User findUserById(Long userId);
}
