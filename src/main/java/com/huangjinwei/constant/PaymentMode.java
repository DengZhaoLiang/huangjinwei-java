package com.huangjinwei.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author Liang
 * Created By 2020/12/22
 * 支付方式枚举类
 */
@RequiredArgsConstructor
public enum PaymentMode {

    /**
     * 微信支付
     */
    WE_CHAT(1),

    /**
     * 支付宝支付
     */
    ALIPAY(2),

    ;

    @Getter
    private final int code;
}
