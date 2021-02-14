package com.huangjinwei.utils;

import com.huangjinwei.mapper.OrderSnMapper;
import com.huangjinwei.model.OrderSn;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.time.Instant;
import java.util.UUID;

@Slf4j
@Component
public class OrderSnGenerateUtil {

    @Autowired
    private OrderSnMapper mOrderSnMapper;

    /**
     * 生成订单号
     */
    public String generate() {
        int retry = 5;
        String orderSn;
        do {
            orderSn = UUID.randomUUID().toString().replaceAll("-", "");
            log.info("generate code :{}", orderSn);
        } while ((!CollectionUtils.isEmpty(mOrderSnMapper.existsByOrderSn(orderSn))) && retry-- > 0);
        if (retry <= 0) {
            throw new RuntimeException("订单号生成失败");
        }

        OrderSn pojo = new OrderSn();
        pojo.setNo(orderSn);
        pojo.setCreatedAt(Instant.now().getEpochSecond());
        pojo.setUpdatedAt(Instant.now().getEpochSecond());
        if (mOrderSnMapper.insert(pojo) > 0) {
            return orderSn;
        }
        throw new RuntimeException("订单号生成失败");
    }

}
