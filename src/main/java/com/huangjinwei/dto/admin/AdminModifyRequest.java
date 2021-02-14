package com.huangjinwei.dto.admin;

import lombok.Data;

/**
 * @author huangjinwei
 * 2021-01-30
 */
@Data
public class AdminModifyRequest {

    /**
     * 姓名
     */
    private String name;

    /**
     * 账号
     */
    private String account;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 密码
     */
    private String password;
}
