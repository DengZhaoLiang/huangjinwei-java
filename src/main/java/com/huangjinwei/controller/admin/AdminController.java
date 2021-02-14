package com.huangjinwei.controller.admin;

import com.huangjinwei.dto.admin.AdminModifyRequest;
import com.huangjinwei.dto.admin.AdminRequest;
import com.huangjinwei.dto.admin.AdminResponse;
import com.huangjinwei.service.admin.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author huangjinwei
 * 2021-01-30
 */
@Validated
@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private AdminService mAdminService;

    @PostMapping("")
    public AdminResponse login(@Validated @RequestBody AdminRequest request) {
        return mAdminService.login(request);
    }

    @GetMapping("")
    public AdminResponse profile() {
        return mAdminService.profile();
    }

    @PutMapping("")
    public void modify(@Validated @RequestBody AdminModifyRequest request) {
        mAdminService.modify(request);
    }
}
