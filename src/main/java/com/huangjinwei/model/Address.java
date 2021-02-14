package com.huangjinwei.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @author huangjinwei
 * 2021-01-30
 */
@Data
public class Address {

    /**
     * 地址ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 收件人
     */
    private String name;

    /**
     * 电话号码
     */
    private String phone;

    /**
     * 详细地址
     */
    private String detail;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 创建时间
     */
    private Long createdAt;

    /**
     * 更新时间
     */
    private Long updatedAt;
}
