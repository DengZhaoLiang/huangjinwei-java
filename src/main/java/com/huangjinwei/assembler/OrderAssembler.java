package com.huangjinwei.assembler;

import com.huangjinwei.dto.admin.order.AdminOrderResponse;
import com.huangjinwei.dto.api.order.OrderResponse;
import com.huangjinwei.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * @author huangjinwei
 * 2021-02-03
 */
@Mapper(componentModel = "spring")
public interface OrderAssembler {

    @Mapping(target = "name", ignore = true)
    AdminOrderResponse toResponse(Order order);

    @Mapping(target = "books", ignore = true)
    @Mapping(target = "address", ignore = true)
    OrderResponse toApiResponse(Order order);
}
