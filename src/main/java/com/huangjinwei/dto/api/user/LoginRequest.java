package com.huangjinwei.dto.api.user;

import lombok.Data;

/**
 * @author huangjinwei
 * 2021-02-10
 */
@Data
public class LoginRequest {

    /**
     * 手机号
     */
    private String phone;

    /**
     * 密码
     */
    private String password;
}
