package com.huangjinwei.dto.api.order;

import lombok.Data;

/**
 * @author huangjinwei
 * 2021-02-13
 */
@Data
public class OrderProductResponse {

    /**
     * 商品Id
     */
    private Long productId;

    /**
     * 购买数量
     */
    private Integer purchaseNum;
}
