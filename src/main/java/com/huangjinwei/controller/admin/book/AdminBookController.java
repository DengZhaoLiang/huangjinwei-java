package com.huangjinwei.controller.admin.book;

import com.huangjinwei.dto.IDSRequest;
import com.huangjinwei.dto.admin.Book.AdminBookRequest;
import com.huangjinwei.dto.admin.book.AdminBookResponse;
import com.huangjinwei.model.Book;
import com.huangjinwei.service.admin.book.AdminBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author huangjinwei
 * 2021-02-01
 */
@Validated
@RestController
@RequestMapping("/api/admin/book")
public class AdminBookController {

    @Autowired
    private AdminBookService mAdminBookService;

    @GetMapping("")
    public Page<AdminBookResponse> pageBooks(AdminBookRequest request, Pageable pageable) {
        return mAdminBookService.pageBooks(request, pageable);
    }

    @GetMapping("/{id}")
    public Book detailBook(@PathVariable Long id) {
        return mAdminBookService.detailBook(id);
    }

    @PostMapping("")
    public void insertBook(@RequestBody @Validated Book book) {
        mAdminBookService.insertBook(book);
    }

    @PutMapping("")
    public void updateBook(@RequestBody @Validated Book book) {
        mAdminBookService.updateBook(book);
    }

    @PutMapping("/{id}/{status}")
    public void statusBook(@PathVariable Long id, @PathVariable Integer status) {
        mAdminBookService.statusBook(id, status);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        mAdminBookService.deleteBook(id);
    }

    @DeleteMapping("")
    public void batchDeleteBooks(@RequestBody @Validated IDSRequest request) {
        mAdminBookService.batchDeleteBooks(request.getIds());
    }
}
