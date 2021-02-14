package com.huangjinwei.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.huangjinwei.model.OrderSn;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author huangjinwei
 * 2021-02-03
 */
@Mapper
@Repository
public interface OrderSnMapper extends BaseMapper<OrderSn> {

    /**
     * 通过订单号查询是否存在
     */
    List<OrderSn> existsByOrderSn(@Param("orderSn") String orderSn);
}
