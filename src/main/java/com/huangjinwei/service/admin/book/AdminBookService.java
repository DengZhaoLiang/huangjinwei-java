package com.huangjinwei.service.admin.book;

import com.huangjinwei.dto.admin.Book.AdminBookRequest;
import com.huangjinwei.dto.admin.book.AdminBookResponse;
import com.huangjinwei.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author huangjinwei
 * 2021-02-01
 */
public interface AdminBookService {

    /**
     * 分页获取书列表
     */
    Page<AdminBookResponse> pageBooks(AdminBookRequest request, Pageable pageable);

    /**
     * 删除书
     */
    void deleteBook(Long id);

    /**
     * 批量删除书
     */
    void batchDeleteBooks(List<Long> ids);

    /**
     * 新增书
     */
    void insertBook(Book book);

    /**
     * 修改书
     */
    void updateBook(Book book);

    /**
     * 获取书详情
     */
    Book detailBook(Long id);

    /**
     * 上下架书
     */
    void statusBook(Long id, Integer status);
}
