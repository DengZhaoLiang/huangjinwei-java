package com.huangjinwei.service.admin;

import com.huangjinwei.dto.admin.AdminModifyRequest;
import com.huangjinwei.dto.admin.AdminRequest;
import com.huangjinwei.dto.admin.AdminResponse;

/**
 * @author huangjinwei
 * 2021-01-30
 */
public interface AdminService {

    /**
     * 获取管理员详情
     */
    AdminResponse profile();

    /**
     * 管理员登录
     */
    AdminResponse login(AdminRequest request);

    /**
     * 修改管理员信息
     */
    void modify(AdminModifyRequest request);
}
