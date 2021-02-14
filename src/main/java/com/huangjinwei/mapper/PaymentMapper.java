package com.huangjinwei.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.huangjinwei.model.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author Liang
 * Created By 2021/2/3
 */
@Mapper
@Repository
public interface PaymentMapper extends BaseMapper<Payment> {
}
