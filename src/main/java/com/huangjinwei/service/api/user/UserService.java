package com.huangjinwei.service.api.user;

import com.huangjinwei.dto.api.user.LoginRequest;
import com.huangjinwei.model.User;

/**
 * @author huangjinwei
 * 2021-02-10
 */
public interface UserService {

    /**
     * 登录
     */
    User login(LoginRequest request);

    /**
     * 注册
     */
    void register(User user);

    /**
     * 更新用户
     */
    User updateUser(User user);
}
