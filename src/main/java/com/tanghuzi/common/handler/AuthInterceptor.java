package com.tanghuzi.common.handler;

import com.tanghuzi.common.auth.AuthIgnore;
import com.tanghuzi.common.config.AppConfig;
import com.tanghuzi.common.entity.UserInfo;
import com.tanghuzi.common.enums.ResultCode;
import com.tanghuzi.common.entity.CommonResp;
import com.tanghuzi.common.utils.HttpRequestUtil;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * 用户授权拦截器
 * @author: Liming
 * @date: 2018/12/17 15:22
 * @version 1.0
 **/
@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Autowired
    private AppConfig config;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
    {
        //过滤无关的请求
        if(!HandlerMethod.class.isInstance(handler))
        {
            return true;
        }
        //获取请求接口方法上的注解
        HandlerMethod handlerMethod=(HandlerMethod)handler;
        AuthIgnore ignore=handlerMethod.getMethodAnnotation(AuthIgnore.class);
        //判断是否有忽视登录授权注解
        if(ignore!=null)
        {
            //无需验证登陆授权
            return true;
        }else
        {
            //从请求头中获取accessToken
            String token=request.getHeader("authorization");

            //TODO:临时使用
            //token="123456";

            CommonResp<UserInfo> resp=new CommonResp<>();
            if(token==null||"".equals(token)||"null".equals(token))
            {
                resp.setResultCode(ResultCode.NoAuth);
                resp.setResultMsg("授权码(authorization)不能为空");
                writeErrorMsg(response,resp);
                allowOriginHeader(response);
                return false;
            }else
            {
                //验证登录授权是否有效
                System.out.println("授权验证地址"+config.getAuthValidationUrl()+token);
                String result=HttpRequestUtil.sendRequestDoGet(config.getAuthValidationUrl()+token);
                System.out.println("授权验证结果"+result);
                JSONObject jsonObject=JSONObject.fromObject(result);
                Map<String, Class> classMap = new HashMap<String, Class>();
                classMap.put("data", UserInfo.class);
                resp=(CommonResp<UserInfo>) JSONObject.toBean(jsonObject,CommonResp.class,classMap);
                if(resp.getResultCode()== ResultCode.Success)
                {
                    request.setAttribute("userId",resp.getData().getUserId());
                    request.setAttribute("nickName",resp.getData().getNickName());
                    request.setAttribute("headImg",resp.getData().getHeadImg());
                    request.setAttribute("openId",resp.getData().getOpenId());
                    return true;
                }else
                {
                    writeErrorMsg(response,resp);
                    allowOriginHeader(response);
                    //throw new CoreException(resp.getResultCode(),resp.getResultMsg());
                    return  false;
                }
            }

            /*
            //判断用户是否登录
            CommonResp<String> resp= AuthSessionUtil.checkAuth(request.getSession());//UserUtil.getCurrentUserIdStr(request.getSession(), CoreConfig.USERID_PROPERTYNAME);
            if(resp.getResultCode()== ResultCode.Success)
            {
                return true;
            }else
            {
                response.setContentType("text/json;charset=UTF-8");
                request.setCharacterEncoding("utf-8");
                JsonConfig jsonConfig = new JsonConfig();
                //设置不转换属性
                jsonConfig.setExcludes(new String[] {"resultCode","resultMsg"});
                JSONObject json=JSONObject.fromObject(resp,jsonConfig);
                //System.out.println(json);
                response.getWriter().println(json);
                return  false;
            }
            */
        }
    }

    private void allowOriginHeader(HttpServletResponse response)
    {
        response.setContentType("application/json; charset=utf-8");
        response.setCharacterEncoding("utf-8");
        /* 允许跨域的主机地址 */
        response.setHeader("Access-Control-Allow-Origin", "*");
        /* 允许跨域的请求方法GET, POST, HEAD 等 */
        response.setHeader("Access-Control-Allow-Methods", "*");
        /* 重新预检验跨域的缓存时间 (s) */
        response.setHeader("Access-Control-Max-Age", "3600");
        /* 允许跨域的请求头 */
        response.setHeader("Access-Control-Allow-Headers", "*");
        /* 是否携带cookie */
        response.setHeader("Access-Control-Allow-Credentials", "true");
    }
    /**
     * 处理授权验证未通过的响应信息
     * @param response 响应对象
     * @param resp 请求返回结果信息
     * @throws Exception 抛出异常
     */
    private void writeErrorMsg(HttpServletResponse response,CommonResp resp) throws Exception
    {
        response.setContentType("text/json;charset=UTF-8");
        //request.setCharacterEncoding("utf-8");
        JsonConfig jsonConfig = new JsonConfig();
        //设置不转换属性
        jsonConfig.setExcludes(new String[] {"resultCode","resultMsg"});
        JSONObject json=JSONObject.fromObject(resp,jsonConfig);
        //System.out.println(json);
        response.getWriter().println(json);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
