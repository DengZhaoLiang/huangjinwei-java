package com.huangjinwei.service.admin.product;

import com.huangjinwei.dto.admin.product.AdminProductRequest;
import com.huangjinwei.dto.admin.product.AdminProductResponse;
import com.huangjinwei.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author huangjinwei
 * 2021-02-01
 */
public interface AdminProductService {

    /**
     * 分页获取商品列表
     */
    Page<AdminProductResponse> pageProducts(AdminProductRequest request, Pageable pageable);

    /**
     * 删除商品
     */
    void deleteProduct(Long id);

    /**
     * 批量删除商品
     */
    void batchDeleteProducts(List<Long> ids);

    /**
     * 新增商品
     */
    void insertProduct(Product product);

    /**
     * 修改商品
     */
    void updateProduct(Product product);

    /**
     * 获取商品详情
     */
    Product detailProduct(Long id);

    /**
     * 上下架商品
     */
    void statusProduct(Long id, Integer status);
}
