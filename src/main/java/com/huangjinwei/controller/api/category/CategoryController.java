package com.huangjinwei.controller.api.category;

import com.huangjinwei.service.api.category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author huangjinwei
 * 2021-02-15
 */
@Validated
@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    private CategoryService mCategoryService;

    @GetMapping("")
    public List<String> listCategories() {
        return mCategoryService.listCategories();
    }
}
