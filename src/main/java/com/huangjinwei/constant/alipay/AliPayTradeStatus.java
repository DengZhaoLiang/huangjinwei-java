package com.huangjinwei.constant.alipay;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;

/**
 * Created by hulk on 2019-08-15.
 */
@RequiredArgsConstructor
public enum AliPayTradeStatus {

    WAIT_BUYER_PAY("WAIT_BUYER_PAY", "交易创建，等待买家付款"),

    TRADE_CLOSED("TRADE_CLOSED", "未付款交易超时关闭，或支付完成后全额退款"),

    TRADE_SUCCESS("TRADE_SUCCESS", "交易支付成功"),

    TRADE_FINISHED("TRADE_FINISHED", "交易结束，不可退款"),

    ;

    @Getter
    private final String code;

    @Getter
    private final String msg;

    public static AliPayTradeStatus fromCode(String code) {

        if (StringUtils.pathEquals(code, WAIT_BUYER_PAY.code)) {
            return WAIT_BUYER_PAY;
        }
        if (StringUtils.pathEquals(code, TRADE_CLOSED.code)) {
            return TRADE_CLOSED;
        }
        if (StringUtils.pathEquals(code, TRADE_SUCCESS.code)) {
            return TRADE_SUCCESS;
        }
        if (StringUtils.pathEquals(code, TRADE_FINISHED.code)) {
            return TRADE_FINISHED;
        }

        throw new RuntimeException("code error");
    }
}
