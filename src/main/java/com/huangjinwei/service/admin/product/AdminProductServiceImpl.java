package com.huangjinwei.service.admin.product;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.huangjinwei.assembler.ProductAssembler;
import com.huangjinwei.dto.admin.product.AdminProductRequest;
import com.huangjinwei.dto.admin.product.AdminProductResponse;
import com.huangjinwei.mapper.ProductMapper;
import com.huangjinwei.model.Product;
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
public class AdminProductServiceImpl implements AdminProductService {

    @Autowired
    private ProductMapper mProductMapper;

    @Autowired
    private ProductAssembler mProductAssembler;

    @Override
    public Page<AdminProductResponse> pageProducts(AdminProductRequest request, Pageable pageable) {
        PageRequest page = PageRequest.of(pageable.getPageNumber() - 1, pageable.getPageSize());
        QueryWrapper<Product> wrapper = new QueryWrapper<>();
        wrapper.like(Strings.isNotBlank(request.getName()), "name", request.getName());
        List<AdminProductResponse> products = mProductMapper.selectList(wrapper).stream()
                .map(product -> mProductAssembler.toResponse(product))
                .collect(Collectors.toList());
        return new PageImpl<>(products.stream()
                .skip((page.getPageNumber()) * page.getPageSize())
                .limit(page.getPageSize())
                .collect(Collectors.toList()), page, products.size());
    }

    @Override
    public void deleteProduct(Long id) {
        mProductMapper.deleteById(id);
    }

    @Override
    public void batchDeleteProducts(List<Long> ids) {
        mProductMapper.deleteBatchIds(ids);
    }

    @Override
    public void insertProduct(Product product) {
        product.setId(null);
        product.setCreatedAt(Instant.now().getEpochSecond());
        product.setUpdatedAt(Instant.now().getEpochSecond());
        mProductMapper.insert(product);
    }

    @Override
    public void updateProduct(Product product) {
        UpdateWrapper<Product> wrapper = new UpdateWrapper<>();
        wrapper.set(Strings.isNotBlank(product.getName()), "name", product.getName());
        wrapper.set(Strings.isNotBlank(product.getImage()), "image", product.getImage());
        wrapper.set(Objects.nonNull(product.getPrice()), "price", product.getPrice());
        wrapper.set(Objects.nonNull(product.getInventory()), "inventory", product.getInventory());
        wrapper.eq("id", product.getId());
        Product update = mProductMapper.selectById(product.getId());
        update.setUpdatedAt(Instant.now().getEpochSecond());
        mProductMapper.update(update, wrapper);
    }

    @Override
    public Product detailProduct(Long id) {
        return mProductMapper.selectById(id);
    }

    @Override
    public void statusProduct(Long id, Integer status) {
        UpdateWrapper<Product> wrapper = new UpdateWrapper<>();
        wrapper.set(Objects.nonNull(status), "status", status);
        wrapper.eq("id", id);
        Product update = mProductMapper.selectById(id);
        update.setUpdatedAt(Instant.now().getEpochSecond());
        mProductMapper.update(update, wrapper);
    }
}
