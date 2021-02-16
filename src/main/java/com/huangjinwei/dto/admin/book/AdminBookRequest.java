package com.huangjinwei.dto.admin.book;

import lombok.Data;

/**
 * @author huangjinwei
 * 2021-02-01
 */
@Data
public class AdminBookRequest {

    /**
     * 书名
     */
    private String name;

    /**
     * 类别Id
     */
    private Long categoryId;
}
