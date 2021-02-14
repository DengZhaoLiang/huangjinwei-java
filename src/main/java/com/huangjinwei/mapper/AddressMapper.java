package com.huangjinwei.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.huangjinwei.model.Address;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author huangjinwei
 * 2021-02-11
 */
@Mapper
@Repository
public interface AddressMapper extends BaseMapper<Address> {
}
