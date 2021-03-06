package com.huangjinwei.service.api.order;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.huangjinwei.assembler.OrderAssembler;
import com.huangjinwei.assembler.OrderBookAssembler;
import com.huangjinwei.constant.PaymentType;
import com.huangjinwei.dto.api.order.AliPayResponse;
import com.huangjinwei.dto.api.order.OrderRequest;
import com.huangjinwei.dto.api.order.OrderResponse;
import com.huangjinwei.mapper.AddressMapper;
import com.huangjinwei.mapper.BookMapper;
import com.huangjinwei.mapper.OrderBookMapper;
import com.huangjinwei.mapper.OrderMapper;
import com.huangjinwei.model.Book;
import com.huangjinwei.model.Order;
import com.huangjinwei.model.OrderBook;
import com.huangjinwei.service.alipay.AliPayService;
import com.huangjinwei.utils.OrderSnGenerateUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

/**
 * @author huangjinwei
 * 2021-02-12
 */
@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper mOrderMapper;

    @Autowired
    private OrderAssembler mOrderAssembler;

    @Autowired
    private OrderBookMapper mOrderBookMapper;

    @Autowired
    private OrderBookAssembler mOrderBookAssembler;

    @Autowired
    private AddressMapper mAddressMapper;

    @Autowired
    private AliPayService mAliPayService;

    @Autowired
    private OrderSnGenerateUtil mOrderSnGenerateUtil;

    @Autowired
    private BookMapper mBookMapper;

    @Override
    public Page<OrderResponse> pageOrders(Long userId, Pageable pageable) {
        PageRequest page = PageRequest.of(pageable.getPageNumber() - 1, pageable.getPageSize());
        List<OrderResponse> orders = mOrderMapper.listByUserId(userId)
                .stream()
                .map(order -> {
                    OrderResponse response = mOrderAssembler.toApiResponse(order);
                    QueryWrapper<OrderBook> wrapper = new QueryWrapper<>();
                    wrapper.eq("order_sn", response.getOrderSn());
                    response.setBooks(
                            mOrderBookMapper.selectList(wrapper)
                                    .stream()
                                    .map(mOrderBookAssembler::toBooks)
                                    .collect(Collectors.toList()));
                    response.setAddress(mAddressMapper.selectById(order.getAddressId()));
                    return response;
                }).collect(Collectors.toList());
        log.info(orders.toString());
        return new PageImpl<>(orders.stream()
                .skip((page.getPageNumber()) * page.getPageSize())
                .limit(page.getPageSize())
                .collect(Collectors.toList()), page, orders.size());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public AliPayResponse createOrder(OrderRequest request) {

        if (!StringUtils.isEmpty(request.getOrderSn()) && !Objects.isNull(request.getTotalPrice())) {
            AliPayResponse response = new AliPayResponse();
            response.setOrderSn(request.getOrderSn());
            response.setTotalPrice(request.getTotalPrice());
            response.setResponse(mAliPayService.createOrder(request.getUserId(), request.getOrderSn(), request.getTotalPrice(), Instant.now().getEpochSecond() + "", PaymentType.BUY).body);
            return response;
        }
        // 生成订单号
        String orderSn = mOrderSnGenerateUtil.generate();

        Order order = new Order();
        order.setAddressId(request.getAddressId());
        order.setUserId(request.getUserId());
        order.setOrderSn(orderSn);
        order.setCreatedAt(Instant.now().getEpochSecond());
        order.setUpdatedAt(Instant.now().getEpochSecond());

        // 循环插入购买的书以及计算总价
        AtomicReference<BigDecimal> totalPrice = new AtomicReference<>(new BigDecimal(0));
        request.getBooks().forEach(it -> {
            Book book = mBookMapper.selectById(it.getBookId());
            if (Objects.isNull(book)) {
                return;
            }
            // 库存为0不能购买
            if (book.getInventory() == 0) {
                throw new RuntimeException("库存为0不能购买");
            }
            // 计算库存量
            if (book.getInventory() < it.getPurchaseNum()) {
                throw new RuntimeException("购买数量大于库存");
            }
            OrderBook orderBook = new OrderBook();
            orderBook.setOrderSn(orderSn);
            orderBook.setBookId(it.getBookId());
            orderBook.setBookName(book.getName());
            orderBook.setBookImage(book.getImage());
            orderBook.setBookPrice(book.getPrice());
            orderBook.setQuantity(it.getPurchaseNum());
            orderBook.setCreatedAt(Instant.now().getEpochSecond());
            orderBook.setUpdatedAt(Instant.now().getEpochSecond());
            mOrderBookMapper.insert(orderBook);

            // 计算总金额
            totalPrice.getAndSet(totalPrice.get().add(new BigDecimal(String.valueOf(book.getPrice().multiply(new BigDecimal(it.getPurchaseNum()))))));
        });

        order.setTotalPrice(totalPrice.get());
        if (mOrderMapper.insertOne(order) > 0) {
            // 下单成功 减少库存
            request.getBooks().forEach(it -> {
                mBookMapper.reduceInventoriesById(it.getBookId(), it.getPurchaseNum());
            });
            AliPayResponse response = new AliPayResponse();
            response.setOrderSn(orderSn);
            response.setTotalPrice(totalPrice.get());
            response.setResponse(mAliPayService.createOrder(request.getUserId(), orderSn, totalPrice.get(), Instant.now().getEpochSecond() + "", PaymentType.BUY).body);
            log.info(response.toString());
            return response;
        }
        return null;
    }

    @Override
    public Boolean isPayed(String orderSn) {
        return mOrderMapper.isPayed(orderSn);
    }
}
