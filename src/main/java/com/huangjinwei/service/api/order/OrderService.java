package com.huangjinwei.service.api.order;

import com.huangjinwei.dto.api.order.AliPayResponse;
import com.huangjinwei.dto.api.order.OrderRequest;
import com.huangjinwei.dto.api.order.OrderResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author huangjinwei
 * 2021-02-12
 */
public interface OrderService {

    /**
     * 分页获取订单
     */
    Page<OrderResponse> pageOrders(Long userId, Pageable pageable);

    /**
     * 创建订单
     */
    AliPayResponse createOrder(OrderRequest request);
}
