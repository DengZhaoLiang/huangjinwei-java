package com.huangjinwei.service.api.user;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.huangjinwei.dto.api.user.LoginRequest;
import com.huangjinwei.mapper.UserMapper;
import com.huangjinwei.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.time.Instant;
import java.util.Optional;

/**
 * @author huangjinwei
 * 2021-02-10
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper mUserMapper;

    @Value("${server.base.url}")
    private String url;

    @Override
    public User login(LoginRequest request) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("phone", request.getPhone());
        wrapper.eq("password", request.getPassword());
        User user = Optional.ofNullable(mUserMapper.selectOne(wrapper))
                .orElseThrow(() -> new RuntimeException("账号密码错误，请重新输入"));
        user.setLastLoginAt(Instant.now().getEpochSecond());
        mUserMapper.updateById(user);
        return user;
    }

    @Override
    public void register(User user) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("phone", user.getPhone());
        Optional.ofNullable(mUserMapper.selectOne(wrapper))
                .ifPresent(it -> {
                    throw new RuntimeException("手机号已被注册");
                });
        user.setName(user.getPhone());
        user.setAvatar(url + "/static/defaultAvatar.jpg");
        mUserMapper.insert(user);
    }

    @Override
    public User updateUser(User user) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("phone", user.getPhone());
        Optional.ofNullable(mUserMapper.selectOne(wrapper))
                .ifPresent(it -> {
                    if (!ObjectUtils.nullSafeEquals(it.getId(), user.getId())) {
                        throw new RuntimeException("手机号已被注册");
                    }
                });
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set(!StringUtils.isEmpty(user.getAvatar()), "avatar", user.getAvatar());
        updateWrapper.set(!StringUtils.isEmpty(user.getName()), "name", user.getName());
        updateWrapper.set(!StringUtils.isEmpty(user.getPhone()), "phone", user.getPhone());
        updateWrapper.set(!StringUtils.isEmpty(user.getPassword()), "password", user.getPassword());
        updateWrapper.eq("id", user.getId());
        mUserMapper.update(user, updateWrapper);
        return mUserMapper.selectById(user.getId());
    }
}
