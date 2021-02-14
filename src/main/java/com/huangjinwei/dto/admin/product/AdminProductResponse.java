package com.huangjinwei.dto.admin.product;

import com.fasterxml.jackson.annotation.JsonGetter;
import lombok.Data;

/**
 * @author huangjinwei
 * 2021-02-01
 */
@Data
public class AdminProductResponse {

    /**
     * 商品ID
     */
    private Long id;

    /**
     * 商品名
     */
    private String name;

    /**
     * 主图
     */
    private String image;

    /**
     * 价格
     */
    private String price;

    /**
     * 库存量
     */
    private Integer inventory;

    /**
     * 状态
     */
    private Integer status;

    @JsonGetter
    public String getStatusStr() {
        return status == 1 ? "上架" : "下架";
    }
}
