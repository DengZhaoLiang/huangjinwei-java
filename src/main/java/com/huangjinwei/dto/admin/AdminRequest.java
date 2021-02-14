package com.huangjinwei.dto.admin;

import lombok.Data;

/**
 * @author huangjinwei
 * 2021-01-30
 */
@Data
public class AdminRequest {

    /**
     * 账号
     */
    private String account;

    /**
     * 密码
     */
    private String password;
}
