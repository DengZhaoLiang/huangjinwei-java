package com.huangjinwei.service.admin;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.huangjinwei.assembler.AdminAssembler;
import com.huangjinwei.dto.admin.AdminModifyRequest;
import com.huangjinwei.dto.admin.AdminRequest;
import com.huangjinwei.dto.admin.AdminResponse;
import com.huangjinwei.mapper.AdminMapper;
import com.huangjinwei.model.Admin;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

/**
 * @author huangjinwei
 * 2021-01-30
 */
@Service
@SuppressWarnings("all")
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper mAdminMapper;

    @Autowired
    private AdminAssembler mAdminAssembler;

    @Override
    public AdminResponse profile() {
        return mAdminAssembler.toResponse(mAdminMapper.selectById(1));
    }

    @Override
    public AdminResponse login(AdminRequest request) {
        QueryWrapper<Admin> wrapper = new QueryWrapper<>();
        wrapper.eq("account", request.getAccount());
        wrapper.eq("password", request.getPassword());
        Admin admin = mAdminMapper.selectOne(wrapper);
        ;
        if (ObjectUtils.isEmpty(admin)) {
            throw new RuntimeException("账号密码错误，请重新输入");
        }
        return mAdminAssembler.toResponse(admin);
    }

    @Override
    public void modify(AdminModifyRequest request) {
        UpdateWrapper wrapper = new UpdateWrapper<Admin>();
        wrapper.set(!StringUtils.isEmpty(request.getName()), "name", request.getName());
        wrapper.set(!StringUtils.isEmpty(request.getAccount()), "account", request.getAccount());
        wrapper.set(!StringUtils.isEmpty(request.getAvatar()), "avatar", request.getAvatar());
        wrapper.set(!StringUtils.isEmpty(request.getPassword()), "password", request.getPassword());
        wrapper.eq("id", 1);
        mAdminMapper.update(mAdminMapper.selectById(1), wrapper);
    }
}
