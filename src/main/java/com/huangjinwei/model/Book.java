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
public class Book {


    /**
     * 书ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 书名
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
     * 推荐程度
     */
    private BigDecimal commend;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 类别ID
     */
    private Long categoryId;

    /**
     * 创建时间
     */
    private Long createdAt;

    /**
     * 更新时间
     */
    private Long updatedAt;
}
