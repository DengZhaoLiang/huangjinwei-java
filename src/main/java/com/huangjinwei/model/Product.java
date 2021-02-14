package com.huangjinwei.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author huangjinwei
 * 2021-02-01
 */
@Data
public class Product {


    /**
     * 商品ID
     */
    @TableId(value = "id", type = IdType.AUTO)
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
    private BigDecimal price;

    /**
     * 库存量
     */
    private Integer inventory;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 图文详情
     */
    private String detail;

    /**
     * 创建时间
     */
    private Long createdAt;

    /**
     * 更新时间
     */
    private Long updatedAt;
}
