package com.huangjinwei.dto.api.book;

import lombok.Data;

/**
 * @author zhanzihao
 * 2021-02-15
 */
@Data
public class BookRequest {

    /**
     * 书名
     */
    private String bookName;

    /**
     * 书名
     */
    private String categoryName;
}
