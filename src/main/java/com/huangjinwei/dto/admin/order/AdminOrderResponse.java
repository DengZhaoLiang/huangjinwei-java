package com.huangjinwei.dto.admin.order;

import com.fasterxml.jackson.annotation.JsonGetter;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author huangjinwei
 * 2021-02-03
 */
@Data
public class AdminOrderResponse {

    /**
     * 订单表ID
     */
    private Long id;

    /**
     * 订单号
     */
    private String orderSn;

    /**
     * 购买金额
     */
    private BigDecimal totalPrice;

    /**
     * 书名
     */
    private String name;

    /**
     * 支付成功时间
     */
    private Long payAt;

    /**
     * 付款状态 :1-待支付,2-已支付
     */
    private Integer status;

    /**
     * 创建时间
     */
    private Long createdAt;

    @JsonGetter
    public String getStatusStr() {
        return status == 1 ? "待支付" : "已付款";
    }
}
