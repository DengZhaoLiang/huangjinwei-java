package com.huangjinwei.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.huangjinwei.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author huangjinwei
 * 2021-01-31
 */
@Mapper
@Repository
public interface UserMapper extends BaseMapper<User> {
}
