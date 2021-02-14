package com.huangjinwei.config.alipay;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Liang
 * Created By 2020/12/22
 */
@Data
@ConfigurationProperties(prefix = "alipay")
public class AlipayProperties {
    /**
     * 通信协议，通常填写https
     */
    public String protocol;

    /**
     * 网关域名
     * 线上为：openapi.alipay.com
     * 沙箱为：openapi.alipaydev.com
     */
    public String gatewayHost;

    /**
     * AppId
     */
    public String appId;

    /**
     * 签名类型，Alipay Easy SDK只推荐使用RSA2，估此处固定填写RSA2
     */
    public String signType;

    /**
     * 支付宝公钥
     */
    public String alipayPublicKey;

    /**
     * 应用私钥
     */
    public String merchantPrivateKey;

    /**
     * 应用公钥证书文件路径
     */
    public String merchantCertPath;

    /**
     * 支付宝公钥证书文件路径
     */
    public String alipayCertPath;

    /**
     * 支付宝根证书文件路径
     */
    public String alipayRootCertPath;

    /**
     * 异步通知回调地址（可选）
     */
    public String notifyUrl;

    /**
     * AES密钥（可选）
     */
    public String encryptKey;

    /**
     * 忽略证书校验（可选）
     */
    public boolean ignoreSSL;
}
