package com.tanghuzi.common.config;

/**
 * 核心配置文件
 * @author: Liming
 * @date: 2018/12/17 15:53
 * @version 1.0
 **/
public class CoreConfig {

    /**
     * 用户Id保存在session中属性名称
     */
    public static final String USERID_PROPERTYNAME="CORE_USERID";
    /**
     * 授权信息保存于session中的名称
     */
    public static final String AUTH_PROPERTY_NAME="SESSION_AUTHINFO";
    /**
     * 字符编码格式
     */
    public final static String INPUT_CHARSET="utf-8";
    /**
     * 图形验证码在保存session中的属性名称
     */
    public final static String IMAGECAPTCHA_PROPERTYNAME="ImageCaptcha";
    /**
     * 验证授权接口地址(临时使用,后续会要迭代授权验证这部分的代码)
     */
    //public final static String AUTH_VALIDATION_Url="http://soho.tanghuzi.club/SohoServiceTest/Service/AuthService?cmd=Validation&accessToken=";
}
