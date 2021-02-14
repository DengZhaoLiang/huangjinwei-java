package com.huangjinwei.controller.api.order;

import com.huangjinwei.dto.api.order.AliPayResponse;
import com.huangjinwei.dto.api.order.OrderRequest;
import com.huangjinwei.dto.api.order.OrderResponse;
import com.huangjinwei.service.api.order.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author huangjinwei
 * 2021-02-12
 */
@Validated
@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    private OrderService mOrderService;

    @GetMapping("/{userId}")
    public Page<OrderResponse> pageOrders(Pageable pageable, @PathVariable Long userId) {
        return mOrderService.pageOrders(userId, pageable);
    }

    @PostMapping("buy")
    public AliPayResponse createOrder(@RequestBody @Validated OrderRequest request) {
        return mOrderService.createOrder(request);
    }
}
