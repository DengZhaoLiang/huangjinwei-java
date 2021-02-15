package com.huangjinwei.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.huangjinwei.model.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Liang
 * Created By 2021/2/3
 */
@Mapper
@Repository
public interface OrderMapper extends BaseMapper<Order> {

    List<Order> listByConditions(@Param("orderSn") String orderSn,
                                 @Param("name") String name,
                                 @Param("status") Integer status);

    List<Order> listByUserId(@Param("userId") Long userId);

    int insertOne(@Param("order") Order order);

    Order selectByOrderSn(@Param("orderSn") String orderSn);

    void updateStatus(@Param("orderSn") String orderSn, @Param("status") int status);

    Boolean isPayed(@Param("orderSn") String orderSn);
}
