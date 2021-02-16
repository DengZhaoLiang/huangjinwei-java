package com.huangjinwei.handler.payment;

import com.huangjinwei.constant.PaymentStatus;
import com.huangjinwei.mapper.OrderMapper;
import com.huangjinwei.model.Order;
import com.huangjinwei.model.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;

/**
 * @author huangjinwei
 * 2021-02-13
 */
@Slf4j
@Component
public class BuyHandler implements PaymentHandler {

    @Autowired
    private OrderMapper mOrderMapper;

    @Override
    public void onPaySuccess(Payment payment) {
        Order order = mOrderMapper.selectByOrderSn(payment.getOrderSn());

        // 修改支付时间
        mOrderMapper.updatePayAt(payment.getOrderSn(), Instant.now().getEpochSecond());
        // 修改订单付款状态
        mOrderMapper.updateStatus(payment.getOrderSn(), PaymentStatus.PAID.getCode());
    }
}
