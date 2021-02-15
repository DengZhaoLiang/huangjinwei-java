package com.huangjinwei.service.alipay;

import com.alipay.easysdk.factory.Factory;
import com.alipay.easysdk.kernel.util.ResponseChecker;
import com.alipay.easysdk.payment.common.models.AlipayTradeQueryResponse;
import com.alipay.easysdk.payment.page.models.AlipayTradePagePayResponse;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.CaseFormat;
import com.huangjinwei.constant.PaymentMode;
import com.huangjinwei.constant.PaymentStatus;
import com.huangjinwei.constant.PaymentType;
import com.huangjinwei.constant.alipay.AliPayTradeStatus;
import com.huangjinwei.mapper.PaymentMapper;
import com.huangjinwei.model.Payment;
import com.huangjinwei.model.alipay.AliPayResponse;
import com.huangjinwei.utils.EnumUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Liang
 * Created By 2020/12/23
 */
@Slf4j
@Service
public class AliPayServiceImpl implements AliPayService {

    /**
     * 支付过期时间(分钟)
     */
    @Value("${pay.timeout}")
    private int payTimeout;

    @Autowired
    private ObjectMapper mObjectMapper;

    @Autowired
    private PaymentMapper mPaymentMapper;

    /**
     * 支付宝下单
     */
    @Override
    public AlipayTradePagePayResponse createOrder(Long userId, String orderNo, BigDecimal fee,
                                                  String goodsName, PaymentType paymentType) {
        if (StringUtils.isEmpty(orderNo)) {
            throw new IllegalArgumentException("订单号不能为空");
        }
        if (fee.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("订单金额必须大于0");
        }
        if (StringUtils.isEmpty(goodsName)) {
            throw new IllegalArgumentException("书名称不能为为空");
        }
        QueryWrapper<Payment> wrapper = new QueryWrapper<>();
        wrapper.eq("order_sn", orderNo);
        Payment payment = mPaymentMapper.selectOne(wrapper);
        if (ObjectUtils.isEmpty(payment)) {
            createPayment(userId, orderNo, fee, paymentType);
        }
        AlipayTradePagePayResponse response;
        try {
            response = Factory.Payment.Page().pay(goodsName, orderNo, fee.toString(), "http://localhost:8080/#/shopping/settle?step=4");
            log.info("支付宝下单成功:{}", mObjectMapper.writeValueAsString(response));
        } catch (Exception e) {
            log.error("支付宝下单失败:{}", orderNo);
            throw new RuntimeException(e);
        }
        Instant timeoutAt = Instant.now().plus(payTimeout, ChronoUnit.MINUTES);
        // TODO 延时队列

        return response;
    }

    @Override
    public void aliPayNotify(String orderSn) {
        QueryWrapper<Payment> wrapper = new QueryWrapper<>();
        wrapper.eq("order_sn", orderSn);
        Payment payment = mPaymentMapper.selectOne(wrapper);
        if (payment == null || !ObjectUtils.nullSafeEquals(PaymentStatus.NOT_PAY.getCode(), payment.getStatus())) {
            // 不是待支付的状态 避免重复处理
            return;
        }
        paySuccess(payment, Instant.now());
    }

    /**
     * 支付成功操作
     */
    private void paySuccess(Payment payment, Instant payAt) {
        PaymentType paymentType = EnumUtils.codeOf(payment.getType(), PaymentType.class);
        if (paymentType == null) {
            log.error("订单类型不正确\npayment: {}", payment);
            return;
        }
        Payment updateModel = mPaymentMapper.selectById(payment.getId());
        updateModel.setStatus(PaymentStatus.PAID.getCode());
        updateModel.setPayAt(payAt.getEpochSecond());
        mPaymentMapper.updateById(updateModel);

        // 执行支付成功动作
        paymentType.onPaySuccess(payment);

    }

    /**
     * 统一处理订单handler
     */
    public void handle(String orderNo) {
        AlipayTradeQueryResponse response;
        try {
            response =
                    Factory.Payment.Common().query(orderNo);
        } catch (Exception e) {
            log.error("查询支付宝订单失败:{}", orderNo);
            throw new IllegalArgumentException(e);
        }
        try {
            log.info("支付宝订单信息:{}", mObjectMapper.writeValueAsString(response));
        } catch (JsonProcessingException ignore) {
        }

        QueryWrapper<Payment> wrapper = new QueryWrapper<>();
        wrapper.eq("order_sn", response.getOutTradeNo());
        wrapper.eq("pay_mode", PaymentMode.ALIPAY);
        Payment payment = mPaymentMapper.selectOne(wrapper);
        if (payment == null || !ObjectUtils.nullSafeEquals(PaymentStatus.NOT_PAY.getCode(), payment.getStatus())) {
            return;
        }

        log.info("处理支付宝订单: {}", payment);
        if (ResponseChecker.success(response)) {
            AliPayTradeStatus aliPayTradeStatus =
                    AliPayTradeStatus.fromCode(response.getTradeStatus());
            switch (aliPayTradeStatus) {
                // 未付款状态
                case WAIT_BUYER_PAY:
                    // 交易结束，不可退款
                case TRADE_FINISHED:
                    break;
                // 未付款交易超时关闭，或支付完成后全额退款
                case TRADE_CLOSED:
                    break;
                // 交易支付成功
                case TRADE_SUCCESS:
                    paySuccess(payment, toInstant(response.getSendPayDate()));
                    break;
            }
        }
    }

    /**
     * 创建支付记录
     *
     * @param userId  用户ID
     * @param orderSn 订单号
     * @param fee     金额(元)
     * @param type    类型
     */
    private void createPayment(Long userId, String orderSn, BigDecimal fee, PaymentType type) {
        Payment pojo = new Payment();
        pojo.setOrderSn(orderSn);
        pojo.setUserId(userId);
        pojo.setFee(fee);
        pojo.setRemainFee(fee);
        pojo.setStatus(PaymentStatus.NOT_PAY.getCode());
        pojo.setPayMode(PaymentMode.ALIPAY.getCode());
        pojo.setType(type.getCode());
        pojo.setCreatedAt(Instant.now().getEpochSecond());
        pojo.setUpdatedAt(Instant.now().getEpochSecond());
        mPaymentMapper.insert(pojo);
    }


    /**
     * 转换日期格式
     * yyyy-MM-dd HH:mm:ss
     */
    private Instant toInstant(String str) {
        return StringUtils.isBlank(str) ?
                Instant.now() : LocalDateTime.parse(str,
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
                .toInstant(ZoneOffset.of("+8"));
    }


    private Map<String, String> callbackRequestToMap(HttpServletRequest request) {
        // 获取支付宝POST过来反馈信息
        Map<String, String> params = new HashMap<>();
        Enumeration<?> temp = request.getParameterNames();

        while (temp.hasMoreElements()) {
            String key = (String) temp.nextElement();
            String value = request.getParameter(key);
            // 乱码解决，这段代码在出现乱码时使用。
            // valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, key), value);
        }
        return params;
    }

    private AliPayResponse mapToAliPayResponse(Map<String, String> params) {
        AliPayResponse response = new AliPayResponse();
        response.setNotifyTime(params.get("notify_time"));
        response.setNotifyType(params.get("notify_type"));
        response.setNotifyId(params.get("notify_id"));
        response.setAppId(params.get("app_id"));
        response.setCharset(params.get("charset"));
        response.setVersion(params.get("version"));
        response.setSignType(params.get("sign_type"));
        response.setSign(params.get("sign"));
        response.setTradeNo(params.get("trade_no"));
        response.setOutTradeNo(params.get("out_trade_no"));
        response.setOutBizNo(params.getOrDefault("out_biz_no", ""));
        response.setBuyerId(params.getOrDefault("buyerId", ""));
        response.setBuyerLogonId(params.getOrDefault("buyer_logon_id", ""));
        response.setSellerId(params.getOrDefault("seller_id", ""));
        response.setSellerEmail(params.getOrDefault("seller_email", ""));
        response.setTradeStatus(params.containsKey("trade_status") ?
                AliPayTradeStatus.fromCode(params.get("trade_status")) : null);
        response.setTotalAmount(params.getOrDefault("total_amount", ""));
        response.setReceiptAmount(params.getOrDefault("receipt_amount", ""));
        response.setInvoiceAmount(params.getOrDefault("invoice_amount", ""));
        response.setBuyerPayAmount(params.getOrDefault("buyer_pay_amount", ""));
        response.setPointAmount(params.getOrDefault("point_amount", ""));
        response.setRefundFee(params.getOrDefault("refund_fee", ""));
        response.setSubject(params.getOrDefault("subject", ""));
        response.setBody(params.getOrDefault("body", ""));
        response.setGmtCreate(params.getOrDefault("gmt_create", ""));
        response.setGmtPayment(params.getOrDefault("gmt_payment", ""));
        response.setGmtRefund(params.getOrDefault("gmt_refund", ""));
        response.setGmtClose(params.getOrDefault("gmt_close", ""));
        response.setFundBillList(params.getOrDefault("fund_bill_list", ""));
        response.setPassbackParams(params.getOrDefault("passback_params", ""));
        response.setVoucherDetailList(params.getOrDefault("voucher_detail_list", ""));
        return response;
    }
}
