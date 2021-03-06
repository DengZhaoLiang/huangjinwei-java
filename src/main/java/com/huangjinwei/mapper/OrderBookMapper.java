package com.huangjinwei.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.huangjinwei.model.OrderBook;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author huangjinwei
 * 2021-02-12
 */
@Mapper
@Repository
public interface OrderBookMapper extends BaseMapper<OrderBook> {
}
