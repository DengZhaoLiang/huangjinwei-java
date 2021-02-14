package com.huangjinwei.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author Liang
 * Created By 2021/2/3
 */
@Data
public class Payment {

    /**
     * 支付订单表ID
     */
    @TableId(value = "id", type = IdType.AUTO)
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
     * 订单金额
     */
    private BigDecimal fee;

    /**
     * 剩余可退款金额
     */
    private BigDecimal remainFee;

    /**
     * 付款状态:0-待支付,1-已支付,2-已关闭,3-已退款
     */
    private Integer status;

    /**
     * 支付类型 1 微信 2 支付宝
     */
    private Integer payMode;

    /**
     * 订单类型
     */
    private Integer type;

    /**
     * 支付成功时间
     */
    private Long payAt;

    /**
     * 创建时间
     */
    private Long createdAt;

    /**
     * 更新时间
     */
    private Long updatedAt;
}
