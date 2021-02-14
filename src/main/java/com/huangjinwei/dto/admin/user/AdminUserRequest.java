package com.huangjinwei.dto.admin.user;

import lombok.Data;

/**
 * @author huangjinwei
 * 2021-01-31
 */
@Data
public class AdminUserRequest {

    /**
     * 手机号
     */
    private String phone;

    /**
     * 昵称
     */
    private String name;
}
