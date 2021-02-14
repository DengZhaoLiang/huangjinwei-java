package com.huangjinwei.service.api.book;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.huangjinwei.dto.api.book.BookRequest;
import com.huangjinwei.mapper.BookMapper;
import com.huangjinwei.mapper.CategoryMapper;
import com.huangjinwei.model.Book;
import com.huangjinwei.model.Category;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author huangjinwei
 * 2021-02-10
 */
@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookMapper mBookMapper;

    @Autowired
    private CategoryMapper mCategoryMapper;

    @Override
    public Page<Book> pageBooks(BookRequest request, Pageable pageable) {
        PageRequest page = PageRequest.of(pageable.getPageNumber() - 1, pageable.getPageSize());
        QueryWrapper<Book> wrapper = new QueryWrapper<>();
        wrapper.eq("status", 1);
        wrapper.like(Strings.isNotBlank(request.getBookName()), "name", request.getBookName());
        if (Strings.isNotBlank(request.getCategoryName())) {
            QueryWrapper<Category> categoryQuery = new QueryWrapper<>();
            categoryQuery.eq("name", request.getCategoryName());
            Long categroyId = mCategoryMapper.selectOne(categoryQuery).getId();
            wrapper.eq("category_id", categroyId);
        }
        List<Book> books = mBookMapper.selectList(wrapper);
        return new PageImpl<>(books.stream()
                .skip((page.getPageNumber()) * page.getPageSize())
                .limit(page.getPageSize())
                .collect(Collectors.toList()), page, books.size());
    }

    @Override
    public Book detailBook(Long id) {
        return mBookMapper.selectById(id);
    }

    @Override
    public List<Book> listRecommendBooks() {
        QueryWrapper<Book> wrapper = new QueryWrapper<>();
        wrapper.eq("status", 1);
        wrapper.orderByDesc("updated_at");
        wrapper.last("limit 0,8");
        return mBookMapper.selectList(wrapper);
    }
}
