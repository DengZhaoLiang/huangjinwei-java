package com.huangjinwei.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author Liang
 * Created By 2021/2/3
 */
@Data
@TableName(value = "order_book")
public class OrderBook {

    /**
     * 订单书表ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 订单号
     */
    private String orderSn;

    /**
     * 书ID
     */
    private Long BookId;

    /**
     * 书名
     */
    private String BookName;

    /**
     * 书主图
     */
    private String BookImage;

    /**
     * 单价
     */
    private BigDecimal BookPrice;

    /**
     * 购买数量
     */
    private Integer quantity;

    /**
     * 创建时间
     */
    private Long createdAt;

    /**
     * 更新时间
     */
    private Long updatedAt;
}
