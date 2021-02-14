package com.huangjinwei.service.api.book;

import com.huangjinwei.dto.api.book.BookRequest;
import com.huangjinwei.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author huangjinwei
 * 2021-02-10
 */
public interface BookService {

    /**
     * 获取书
     */
    Page<Book> pageBooks(BookRequest request, Pageable pageable);

    /**
     * 获取书详情
     */
    Book detailBook(Long id);

    /**
     * 获取推荐的书籍
     */
    List<Book> listRecommendBooks();
}
