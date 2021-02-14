package com.huangjinwei.controller.api.user;

import com.huangjinwei.dto.api.user.LoginRequest;
import com.huangjinwei.model.User;
import com.huangjinwei.service.api.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

/**
 * @author huangjinwei
 * 2021-02-10
 */
@Validated
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService mUserService;

    @PostMapping("login")
    public User login(@RequestBody @Validated LoginRequest request) {
        return mUserService.login(request);
    }

    @PostMapping("register")
    public void register(@RequestBody @Validated User user) {
        user.setCreatedAt(Instant.now().getEpochSecond());
        user.setUpdatedAt(Instant.now().getEpochSecond());
        mUserService.register(user);
    }

    @PutMapping("")
    public User updateUser(@RequestBody @Validated User user) {
        user.setUpdatedAt(Instant.now().getEpochSecond());
        return mUserService.updateUser(user);
    }
}
