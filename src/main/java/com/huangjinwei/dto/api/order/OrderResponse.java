package com.huangjinwei.dto.api.order;

import com.huangjinwei.model.Address;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author huangjinwei
 * 2021-02-12
 */
@Data
public class OrderResponse {

    /**
     * 订单表ID
     */
    private Long id;

    /**
     * 订单号
     */
    private String orderSn;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 购买金额
     */
    private BigDecimal totalPrice;

    /**
     * 地址
     */
    private Address address;

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

    /**
     * 更新时间
     */
    private Long updatedAt;

    /**
     * 产品列表
     */
    private List<BookResponse> books;
}
