package com.huangjinwei.service.api.category;

import com.huangjinwei.mapper.CategoryMapper;
import com.huangjinwei.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author huangjinwei
 * 2021-02-15
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper mCategoryMapper;

    @Override
    public List<String> listCategories() {
        return mCategoryMapper.selectList(null)
                .stream()
                .map(Category::getName)
                .collect(Collectors.toList());
    }
}
