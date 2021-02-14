package com.huangjinwei.controller.admin.order;

import com.huangjinwei.dto.admin.order.AdminOrderRequest;
import com.huangjinwei.dto.admin.order.AdminOrderResponse;
import com.huangjinwei.service.admin.order.AdminOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author huangjinwei
 * 2021-02-03
 */
@Validated
@RestController
@RequestMapping("/api/admin/order")
public class AdminOrderController {

    @Autowired
    private AdminOrderService mAdminOrderService;

    @GetMapping("")
    public Page<AdminOrderResponse> pageOrders(AdminOrderRequest request, Pageable pageable) {
        return mAdminOrderService.pageOrders(request, pageable);
    }
}
