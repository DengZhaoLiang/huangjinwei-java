package com.huangjinwei.controller.admin.category;

import com.huangjinwei.dto.IDSRequest;
import com.huangjinwei.model.Category;
import com.huangjinwei.service.admin.category.AdminCategoryService;
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

import java.util.List;

/**
 * @author huangjinwei
 * 2021-02-17
 */
@Validated
@RestController
@RequestMapping("/api/admin/category")
public class AdminCategoryController {

    @Autowired
    private AdminCategoryService mAdminCategoryService;

    @GetMapping("")
    public Page<Category> pageCategories(Pageable pageable) {
        return mAdminCategoryService.pageCategories(pageable);
    }

    @GetMapping("/{id}")
    public Category detailCategory(@PathVariable Long id) {
        return mAdminCategoryService.detailCategory(id);
    }

    @PostMapping("")
    public void insertCategory(@RequestBody @Validated Category category) {
        mAdminCategoryService.insertCategory(category);
    }

    @PutMapping("")
    public void updateCategory(@RequestBody @Validated Category category) {
        mAdminCategoryService.updateCategory(category);
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable Long id) {
        mAdminCategoryService.deleteCategory(id);
    }

    @DeleteMapping("")
    public void batchDeleteCategories(@RequestBody @Validated IDSRequest request) {
        mAdminCategoryService.batchDeleteCategories(request.getIds());
    }

    @GetMapping("/list")
    public List<Category> listCategories() {
        return mAdminCategoryService.listCategories();
    }
}
