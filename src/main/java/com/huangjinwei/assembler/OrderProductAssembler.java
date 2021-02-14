package com.huangjinwei.assembler;

import com.huangjinwei.dto.api.order.ProductResponse;
import com.huangjinwei.model.OrderProduct;
import org.mapstruct.Mapper;

/**
 * @author huangjinwei
 * 2021-02-12
 */
@Mapper(componentModel = "spring")
public interface OrderProductAssembler {

    ProductResponse toProducts(OrderProduct orderProduct);
}
