package com.huangjinwei.config.alipay;

import com.alipay.easysdk.factory.Factory;
import com.alipay.easysdk.kernel.Config;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;

/**
 * @author Liang
 * Created By 2020/12/22
 */
@Configuration
@EnableConfigurationProperties(AlipayProperties.class)
public class AlipayConfig {

    @Autowired
    private AlipayProperties mAlipayProperties;

    @PostConstruct
    public void init() {
        Factory.setOptions(getOptions());
    }

    private Config getOptions() {
        Config config = new Config();
        config.protocol = StringUtils.isEmpty(mAlipayProperties.getProtocol()) ?
                "https" : mAlipayProperties.getProtocol();
        config.gatewayHost = StringUtils.isEmpty(mAlipayProperties.getGatewayHost()) ?
                "openapi.alipaydev.com" : mAlipayProperties.getGatewayHost();
        config.signType = StringUtils.isEmpty(mAlipayProperties.getSignType()) ?
                "RSA2" : mAlipayProperties.getSignType();

        config.appId = StringUtils.isEmpty(mAlipayProperties.getAppId()) ?
                "" : mAlipayProperties.getAppId();

        // 为避免私钥随源码泄露，推荐从文件中读取私钥字符串而不是写入源码中
        config.merchantPrivateKey = StringUtils.isEmpty(mAlipayProperties.getMerchantPrivateKey()) ?
                "" : mAlipayProperties.getMerchantPrivateKey();

        //注：证书文件路径支持设置为文件系统中的路径或CLASS_PATH中的路径，优先从文件系统中加载，加载失败后会继续尝试从CLASS_PATH中加载
        config.merchantCertPath = StringUtils.isEmpty(mAlipayProperties.getMerchantCertPath()) ?
                "" : mAlipayProperties.getMerchantCertPath();
        config.alipayCertPath = StringUtils.isEmpty(mAlipayProperties.getAlipayCertPath()) ?
                "" : mAlipayProperties.getAlipayCertPath();
        config.alipayRootCertPath = StringUtils.isEmpty(mAlipayProperties.getAlipayRootCertPath()) ?
                "" : mAlipayProperties.getAlipayRootCertPath();

        //注：如果采用非证书模式，则无需赋值上面的三个证书路径，改为赋值如下的支付宝公钥字符串即可
        config.alipayPublicKey = StringUtils.isEmpty(mAlipayProperties.getAlipayPublicKey()) ?
                "" : mAlipayProperties.getAlipayPublicKey();

        //可设置异步通知接收服务地址（可选）
        config.notifyUrl = StringUtils.isEmpty(mAlipayProperties.getNotifyUrl()) ?
                "" : mAlipayProperties.getNotifyUrl();

        //可设置AES密钥，调用AES加解密相关接口时需要（可选）
        config.encryptKey = StringUtils.isEmpty(mAlipayProperties.getEncryptKey()) ?
                "" : mAlipayProperties.getEncryptKey();

        config.ignoreSSL = !ObjectUtils.isEmpty(mAlipayProperties.isIgnoreSSL())
                && mAlipayProperties.isIgnoreSSL();

        return config;
    }
}
