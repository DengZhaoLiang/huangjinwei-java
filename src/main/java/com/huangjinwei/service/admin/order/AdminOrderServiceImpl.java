package com.huangjinwei.service.admin.order;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.huangjinwei.assembler.OrderAssembler;
import com.huangjinwei.dto.admin.order.AdminOrderRequest;
import com.huangjinwei.dto.admin.order.AdminOrderResponse;
import com.huangjinwei.mapper.OrderBookMapper;
import com.huangjinwei.mapper.OrderMapper;
import com.huangjinwei.model.OrderBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

/**
 * @author huangjinwei
 * 2021-02-03
 */
@Service
public class AdminOrderServiceImpl implements AdminOrderService {

    @Autowired
    private OrderMapper mOrderMapper;

    @Autowired
    private OrderAssembler mOrderAssembler;

    @Autowired
    private OrderBookMapper mOrderBookMapper;

    @Override
    public Page<AdminOrderResponse> pageOrders(AdminOrderRequest request, Pageable pageable) {
        PageRequest page = PageRequest.of(pageable.getPageNumber() - 1, pageable.getPageSize());
        StringJoiner joiner = new StringJoiner("，");
        List<AdminOrderResponse> orders =
                mOrderMapper.listByConditions(request.getOrderSn(), request.getName(), request.getStatus())
                        .stream()
                        .map(order -> {
                            AdminOrderResponse response = mOrderAssembler.toResponse(order);
                            QueryWrapper<OrderBook> wrapper = new QueryWrapper<>();
                            wrapper.eq("order_sn", order.getOrderSn());
                            mOrderBookMapper.selectList(wrapper)
                                    .stream()
                                    .map(OrderBook::getBookName)
                                    .forEach(joiner::add);
                            response.setName(joiner.toString());
                            return response;
                        })
                        .collect(Collectors.toList());
        return new PageImpl<>(orders.stream()
                .skip((page.getPageNumber()) * page.getPageSize())
                .limit(page.getPageSize())
                .collect(Collectors.toList()), page, orders.size());
    }
}
