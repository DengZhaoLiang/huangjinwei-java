package com.huangjinwei.dto.api.order;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author huangjinwei
 * 2021-02-12
 */
@Data
public class ProductResponse {

    /**
     * 商品ID
     */
    private Long productId;

    /**
     * 商品名
     */
    private String productName;

    /**
     * 商品主图
     */
    private String productImage;

    /**
     * 单价
     */
    private BigDecimal productPrice;

    /**
     * 购买数量
     */
    private Integer quantity;
}
