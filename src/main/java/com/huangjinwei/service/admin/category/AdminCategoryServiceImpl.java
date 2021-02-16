package com.huangjinwei.service.admin.category;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.huangjinwei.dto.admin.book.AdminBookResponse;
import com.huangjinwei.mapper.CategoryMapper;
import com.huangjinwei.model.Category;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author huangjinwei
 * 2021-02-17
 */
@Service
public class AdminCategoryServiceImpl implements AdminCategoryService {

    @Autowired
    private CategoryMapper mCategoryMapper;

    @Override
    public Page<Category> pageCategories(Pageable pageable) {
        PageRequest page = PageRequest.of(pageable.getPageNumber() - 1, pageable.getPageSize());
        List<Category> categories = mCategoryMapper.selectList(null);
        return new PageImpl<>(categories.stream()
                .skip((page.getPageNumber()) * page.getPageSize())
                .limit(page.getPageSize())
                .collect(Collectors.toList()), page, categories.size());
    }

    @Override
    public Category detailCategory(Long id) {
        return mCategoryMapper.selectById(id);
    }

    @Override
    public void insertCategory(Category category) {
        QueryWrapper<Category> query = new QueryWrapper<>();
        query.eq("name", category.getName());
        Optional.ofNullable(mCategoryMapper.selectOne(query))
                .ifPresent(it -> {
                    throw new RuntimeException("类别名称已存在");
                });
        category.setId(null);
        category.setCreatedAt(Instant.now().getEpochSecond());
        category.setUpdatedAt(Instant.now().getEpochSecond());
        mCategoryMapper.insert(category);
    }

    @Override
    public void updateCategory(Category category) {
        QueryWrapper<Category> query = new QueryWrapper<>();
        query.eq("name", category.getName());
        Optional.ofNullable(mCategoryMapper.selectOne(query))
                .ifPresent(it -> {
                    if (!ObjectUtils.nullSafeEquals(it.getId(), category.getId())) {
                        throw new RuntimeException("类别名称已存在");
                    }
                });
        UpdateWrapper<Category> wrapper = new UpdateWrapper<>();
        wrapper.set(!StringUtils.isEmpty(category.getName()), "name", category.getName());
        wrapper.eq("id", category.getId());
        Category update = mCategoryMapper.selectById(category.getId());
        update.setUpdatedAt(Instant.now().getEpochSecond());
        mCategoryMapper.update(update, wrapper);
    }

    @Override
    public void deleteCategory(Long id) {
        mCategoryMapper.deleteById(id);
    }

    @Override
    public void batchDeleteCategories(List<Long> ids) {
        mCategoryMapper.deleteBatchIds(ids);
    }

    @Override
    public List<Category> listCategories() {
        return mCategoryMapper.selectList(null);
    }
}
