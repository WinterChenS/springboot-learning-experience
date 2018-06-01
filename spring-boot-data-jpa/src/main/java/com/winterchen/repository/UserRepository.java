package com.winterchen.repository;

import com.winterchen.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Donghua.Chen on 2018/6/1.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * 根据用户名查询用户信息
     *
     * @param username 用户名
     * @return 查询结果
     */
    List<User> findAllByUsername(String username);

}
