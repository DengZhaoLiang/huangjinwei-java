package com.huangjinwei.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum PaymentStatus {

    /**
     * 待支付
     */
    NOT_PAY(1, "待支付"),

    /**
     * 已支付
     */
    PAID(2, "已支付"),

    /**
     * 已关闭
     */
    CLOSED(3, "已关闭"),

    /**
     * 已退款
     */
    REFUNDED(4, "已退款"),

    ;

    @Getter
    private final int code;

    @Getter
    private final String describe;

    public static String getDescribeByCode(Integer code) {
        switch (code) {
            case 1:
                return NOT_PAY.describe;
            case 2:
                return PAID.describe;
            case 3:
                return CLOSED.describe;
            case 4:
                return REFUNDED.describe;
            default:
                return "";
        }
    }
}
