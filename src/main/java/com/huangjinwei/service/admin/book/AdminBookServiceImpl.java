package com.huangjinwei.service.admin.book;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.huangjinwei.assembler.BookAssembler;
import com.huangjinwei.dto.admin.Book.AdminBookRequest;
import com.huangjinwei.dto.admin.book.AdminBookResponse;
import com.huangjinwei.mapper.BookMapper;
import com.huangjinwei.model.Book;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author huangjinwei
 * 2021-02-01
 */
@Service
public class AdminBookServiceImpl implements AdminBookService {

    @Autowired
    private BookMapper mBookMapper;

    @Autowired
    private BookAssembler mBookAssembler;

    @Override
    public Page<AdminBookResponse> pageBooks(AdminBookRequest request, Pageable pageable) {
        PageRequest page = PageRequest.of(pageable.getPageNumber() - 1, pageable.getPageSize());
        QueryWrapper<Book> wrapper = new QueryWrapper<>();
        wrapper.like(Strings.isNotBlank(request.getName()), "name", request.getName());
        List<AdminBookResponse> Books = mBookMapper.selectList(wrapper).stream()
                .map(Book -> mBookAssembler.toResponse(Book))
                .collect(Collectors.toList());
        return new PageImpl<>(Books.stream()
                .skip((page.getPageNumber()) * page.getPageSize())
                .limit(page.getPageSize())
                .collect(Collectors.toList()), page, Books.size());
    }

    @Override
    public void deleteBook(Long id) {
        mBookMapper.deleteById(id);
    }

    @Override
    public void batchDeleteBooks(List<Long> ids) {
        mBookMapper.deleteBatchIds(ids);
    }

    @Override
    public void insertBook(Book book) {
        book.setId(null);
        book.setCreatedAt(Instant.now().getEpochSecond());
        book.setUpdatedAt(Instant.now().getEpochSecond());
        mBookMapper.insert(book);
    }

    @Override
    public void updateBook(Book book) {
        UpdateWrapper<Book> wrapper = new UpdateWrapper<>();
        wrapper.set(Strings.isNotBlank(book.getName()), "name", book.getName());
        wrapper.set(Strings.isNotBlank(book.getImage()), "image", book.getImage());
        wrapper.set(Objects.nonNull(book.getPrice()), "price", book.getPrice());
        wrapper.set(Objects.nonNull(book.getInventory()), "inventory", book.getInventory());
        wrapper.eq("id", book.getId());
        Book update = mBookMapper.selectById(book.getId());
        update.setUpdatedAt(Instant.now().getEpochSecond());
        mBookMapper.update(update, wrapper);
    }

    @Override
    public Book detailBook(Long id) {
        return mBookMapper.selectById(id);
    }

    @Override
    public void statusBook(Long id, Integer status) {
        UpdateWrapper<Book> wrapper = new UpdateWrapper<>();
        wrapper.set(Objects.nonNull(status), "status", status);
        wrapper.eq("id", id);
        Book update = mBookMapper.selectById(id);
        update.setUpdatedAt(Instant.now().getEpochSecond());
        mBookMapper.update(update, wrapper);
    }
}
