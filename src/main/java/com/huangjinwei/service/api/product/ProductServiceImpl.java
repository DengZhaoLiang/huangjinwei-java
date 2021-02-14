package com.huangjinwei.service.api.product;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.huangjinwei.mapper.ProductMapper;
import com.huangjinwei.model.Product;
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
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper mProductMapper;

    @Override
    public Page<Product> pageProducts(Pageable pageable) {
        PageRequest page = PageRequest.of(pageable.getPageNumber() - 1, pageable.getPageSize());
        QueryWrapper<Product> wrapper = new QueryWrapper<>();
        wrapper.eq("status", 1);
        List<Product> products = mProductMapper.selectList(wrapper);
        return new PageImpl<>(products.stream()
                .skip((page.getPageNumber()) * page.getPageSize())
                .limit(page.getPageSize())
                .collect(Collectors.toList()), page, products.size());
    }

    @Override
    public Product detailProduct(Long id) {
        return mProductMapper.selectById(id);
    }
}
