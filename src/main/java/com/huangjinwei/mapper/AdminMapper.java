package com.huangjinwei.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.huangjinwei.model.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author huangjinwei
 * 2021-01-30
 */
@Mapper
@Repository
public interface AdminMapper extends BaseMapper<Admin> {
}
