package com.huangjinwei.controller.api.product;

import com.huangjinwei.model.Product;
import com.huangjinwei.service.api.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author huangjinwei
 * 2021-02-10
 */
@Validated
@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private ProductService mProductService;

    @GetMapping("")
    public Page<Product> pageProducts(Pageable pageable) {
        return mProductService.pageProducts(pageable);
    }

    @GetMapping("/{id}")
    public Product detailProduct(@PathVariable Long id) {
        return mProductService.detailProduct(id);
    }
}
