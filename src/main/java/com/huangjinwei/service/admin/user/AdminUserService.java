package com.huangjinwei.service.admin.user;

import com.huangjinwei.dto.admin.user.AdminUserRequest;
import com.huangjinwei.dto.admin.user.AdminUserResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author huangjinwei
 * 2021-01-31
 */
public interface AdminUserService {

    /**
     * 分页查看用户列表
     */
    Page<AdminUserResponse> pageUsers(AdminUserRequest request, Pageable pageable);

    /**
     * 删除用户
     */
    void deleteUser(Long id);

    /**
     * 批量删除用户
     */
    void batchDeleteUsers(List<Long> ids);
}
