package com.huangjinwei.dto.api.order;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author huangjinwei
 * 2021-02-13
 */
@Data
public class OrderRequest {

    /**
     * 订单号
     */
    private String orderSn;

    /**
     * 总金额
     */
    private BigDecimal totalPrice;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 地址Id
     */
    private Long addressId;

    /**
     * 购买的产品列表
     */
    private List<OrderBookResponse> Books;
}
