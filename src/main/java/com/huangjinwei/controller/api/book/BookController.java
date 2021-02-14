package com.huangjinwei.controller.api.book;

import com.huangjinwei.dto.api.book.BookRequest;
import com.huangjinwei.model.Book;
import com.huangjinwei.service.api.book.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author huangjinwei
 * 2021-02-10
 */
@Validated
@RestController
@RequestMapping("/api/book")
public class BookController {

    @Autowired
    private BookService mBookService;

    @GetMapping("")
    public Page<Book> pageBooks(BookRequest request, Pageable pageable) {
        return mBookService.pageBooks(request, pageable);
    }

    @GetMapping("/{id}")
    public Book detailBook(@PathVariable Long id) {
        return mBookService.detailBook(id);
    }

    @GetMapping("/recommend")
    public List<Book> listBooks() {
        return mBookService.listRecommendBooks();
    }
}
