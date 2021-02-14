package com.huangjinwei.controller.admin.product;

import com.huangjinwei.dto.IDSRequest;
import com.huangjinwei.dto.admin.product.AdminProductRequest;
import com.huangjinwei.dto.admin.product.AdminProductResponse;
import com.huangjinwei.model.Product;
import com.huangjinwei.service.admin.product.AdminProductService;
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

/**
 * @author huangjinwei
 * 2021-02-01
 */
@Validated
@RestController
@RequestMapping("/api/admin/product")
public class AdminProductController {

    @Autowired
    private AdminProductService mAdminProductService;

    @GetMapping("")
    public Page<AdminProductResponse> pageProducts(AdminProductRequest request, Pageable pageable) {
        return mAdminProductService.pageProducts(request, pageable);
    }

    @GetMapping("/{id}")
    public Product detailProduct(@PathVariable Long id) {
        return mAdminProductService.detailProduct(id);
    }

    @PostMapping("")
    public void insertProduct(@RequestBody @Validated Product product) {
        mAdminProductService.insertProduct(product);
    }

    @PutMapping("")
    public void updateProduct(@RequestBody @Validated Product product) {
        mAdminProductService.updateProduct(product);
    }

    @PutMapping("/{id}/{status}")
    public void statusProduct(@PathVariable Long id, @PathVariable Integer status) {
        mAdminProductService.statusProduct(id, status);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        mAdminProductService.deleteProduct(id);
    }

    @DeleteMapping("")
    public void batchDeleteProducts(@RequestBody @Validated IDSRequest request) {
        mAdminProductService.batchDeleteProducts(request.getIds());
    }
}
