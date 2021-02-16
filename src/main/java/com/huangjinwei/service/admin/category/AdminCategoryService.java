package com.huangjinwei.service.admin.category;

import com.huangjinwei.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author huangjinwei
 * 2021-02-17
 */
public interface AdminCategoryService {

    /**
     * 分页获取类别
     */
    Page<Category> pageCategories(Pageable pageable);

    /**
     * 获取类型详情
     */
    Category detailCategory(Long id);

    /**
     * 新增类别
     */
    void insertCategory(Category category);

    /**
     * 修改类别
     */
    void updateCategory(Category category);

    /**
     * 删除类别
     */
    void deleteCategory(Long id);

    /**
     * 批量删除类别
     */
    void batchDeleteCategories(List<Long> ids);

    List<Category> listCategories();
}
