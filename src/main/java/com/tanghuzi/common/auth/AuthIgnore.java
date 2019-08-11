package com.tanghuzi.common.auth;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 忽视登录授权验证标记
 * @author: Liming
 * @date: 2018/12/17 15:22
 * @version 1.0
 */
@Target(ElementType.METHOD)  //作用于方法之上
@Retention(RetentionPolicy.RUNTIME) //保留至运行时有效
public @interface AuthIgnore {
}
