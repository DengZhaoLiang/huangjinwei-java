package com.huangjinwei.dto.admin.order;

import lombok.Data;

/**
 * @author huangjinwei
 * 2021-02-03
 */
@Data
public class AdminOrderRequest {

    /**
     * 订单号
     */
    private String orderSn;

    /**
     * 书名
     */
    private String name;

    /**
     * 付款状态 :1-待支付,2-已支付
     */
    private Integer status;
}
