package com.huangjinwei.dto.admin.book;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author huangjinwei
 * 2021-02-01
 */
@Data
public class AdminBookResponse {

    /**
     * 书ID
     */
    private Long id;

    /**
     * 书名
     */
    private String name;

    /**
     * 作者
     */
    private String author;

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
     * 推荐程度
     */
    private BigDecimal commend;

    /**
     * 类别名
     */
    private String categoryName;

    /**
     * 状态
     */
    private Integer status;

    @JsonGetter
    public String getStatusStr() {
        return status == 1 ? "上架" : "下架";
    }

    private Long categoryId;
}
