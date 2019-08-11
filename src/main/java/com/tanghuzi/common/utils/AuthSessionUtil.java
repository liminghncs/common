package com.tanghuzi.common.utils;

import com.tanghuzi.common.config.CoreConfig;
import com.tanghuzi.common.enums.ResultCode;
import com.tanghuzi.common.entity.CommonResp;

import javax.servlet.http.HttpSession;

/**
 * 授权信息管理(session模式)
 * @version 1.0
 * @author: Liming
 * @date: 2019/1/5 16:28
 **/
public class AuthSessionUtil {
    /**
     * 保存授权信息
     * @param info 授权信息
     * @param <T> 泛型
     */
    public static <T> void setAuthInfo(HttpSession session, T info)
    {
        session.setAttribute(CoreConfig.AUTH_PROPERTY_NAME,info);
    }
    /**
     * 获取授权信息
     * @param <T> 泛型
     * @return 授权信息
     */
    public static<T> T getAuthInfo(HttpSession session,Class<T> tClass)
    {
        if(session.getAttribute(CoreConfig.AUTH_PROPERTY_NAME)!=null)
        {
            return (T)session.getAttribute(CoreConfig.AUTH_PROPERTY_NAME);
        }else
        {
            return null;
        }
    }

    /**
     * 检测用户是否获取授权
     * @param session session对象
     * @return 操作结果对象
     */
    public static CommonResp<String> checkAuth(HttpSession session)
    {
        CommonResp<String> resp=new CommonResp<>();
        if(session.getAttribute(CoreConfig.AUTH_PROPERTY_NAME)==null)
        {
            resp.setResultCode(ResultCode.NoAuth);
            resp.setResultMsg("用户未登陆或者登陆超时,请重新登陆系统");
        }
        return resp;
    }
    /**
     * 移除授权信息
     * @param session 用户会话对象
     */
    public static void removeAuthInfo(HttpSession session)
    {
        if(session.getAttribute(CoreConfig.AUTH_PROPERTY_NAME)!=null)
        {
            session.removeAttribute(CoreConfig.AUTH_PROPERTY_NAME);
        }
    }

}
