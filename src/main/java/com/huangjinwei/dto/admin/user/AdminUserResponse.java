package com.huangjinwei.dto.admin.user;

import lombok.Data;

/**
 * @author huangjinwei
 * 2021-01-31
 */
@Data
public class AdminUserResponse {

    /**
     * 用户ID
     */
    private Long id;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 昵称
     */
    private String name;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 最近登录时间
     */
    private Long lastLoginAt;

    /**
     * 创建时间
     */
    private Long createdAt;
}
