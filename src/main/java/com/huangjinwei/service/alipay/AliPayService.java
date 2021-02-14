package com.huangjinwei.service.alipay;

import com.alipay.easysdk.payment.page.models.AlipayTradePagePayResponse;
import com.huangjinwei.constant.PaymentType;

import java.math.BigDecimal;

/**
 * @author Liang
 * Created By 2020/12/23
 */
public interface AliPayService {

    /**
     * 支付宝下单
     */
    AlipayTradePagePayResponse createOrder(Long userId, String orderNo, BigDecimal fee,
                                           String goodsName, PaymentType paymentType);

    /**
     * 支付宝订单回调
     */
    void aliPayNotify(String orderSn);
}
