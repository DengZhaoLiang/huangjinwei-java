package com.huangjinwei.handler.payment;


import com.huangjinwei.model.Payment;

/**
 * @author Liang
 * Created By 2020/12/22
 */
public interface PaymentHandler {

    /**
     * 支付成功后执行的动作
     */
    void onPaySuccess(Payment payment);
}
