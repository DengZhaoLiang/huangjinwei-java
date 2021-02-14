package com.huangjinwei.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @author huangjinwei
 * 2021-01-30
 */
@Data
public class Category {

    /**
     * 类别ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 类别
     */
    private String name;

    /**
     * 创建时间
     */
    private Long createdAt;

    /**
     * 更新时间
     */
    private Long updatedAt;
}
