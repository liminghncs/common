package com.tanghuzi.common.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 *  应用程序配置参数
 * @version 1.0
 * @author: Liming
 * @date: 2019/2/9 21:37
 **/
@Component
@ConfigurationProperties(prefix="appconfig.system") //接收application.yml中的AppConfig下面的属性
public class AppConfig {

    /**
     * 登录授权验证地址
     */
    private String authValidationUrl;

    /**
     * 获取登录授权验证地址
     * @return
     */
    public String getAuthValidationUrl() {
        return authValidationUrl;
    }

    /**
     * 设置登录授权验证地址
     * @param authValidationUrl
     */
    public void setAuthValidationUrl(String authValidationUrl) {
        this.authValidationUrl = authValidationUrl;
    }
}
