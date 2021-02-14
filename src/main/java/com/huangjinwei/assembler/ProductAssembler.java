package com.huangjinwei.assembler;

import com.huangjinwei.dto.admin.product.AdminProductResponse;
import com.huangjinwei.model.Product;
import org.mapstruct.Mapper;

/**
 * @author huangjinwei
 * 2021-02-01
 */
@Mapper(componentModel = "spring")
public interface ProductAssembler {

    AdminProductResponse toResponse(Product product);
}
