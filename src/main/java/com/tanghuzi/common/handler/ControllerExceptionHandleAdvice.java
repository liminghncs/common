package com.tanghuzi.common.handler;

import com.tanghuzi.common.enums.ResultCode;
import com.tanghuzi.common.entity.CommonResp;
import com.tanghuzi.common.exception.CoreException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import java.sql.SQLException;
import java.util.List;

/**
 *  全局异常处理类
 * @author: Liming
 * @date: 2018/11/22 0:28
 * @version 1.0
 **/
@RestControllerAdvice
public class ControllerExceptionHandleAdvice {
    //log4j日志记录对象
    private final static Logger logger = LoggerFactory.getLogger(ControllerExceptionHandleAdvice.class);
    @ExceptionHandler
    public CommonResp handler(HttpServletRequest req, HttpServletResponse res, Exception e) {
        logger.error("Rest Http请求发生异常...");

        if (res.getStatus() == HttpStatus.BAD_REQUEST.value()) {
            logger.info("修改返回状态值为200");
            res.setStatus(HttpStatus.OK.value());
        }

        CommonResp resp=new CommonResp();
        if (e instanceof NullPointerException) {
            logger.error("空指针:"+e.getMessage(), e);
            resp.setResultMsg("服务器异常");
            resp.setResultCode(ResultCode.Fail);
        }else if(e instanceof ConstraintViolationException)
        {
            logger.error("参数较验失败：" + e.getMessage(), e);
            resp.setResultCode(ResultCode.ParameteErr);
            String message=e.getMessage();
            //message=message.substring(message.indexOf("."));
            resp.setResultMsg(message);
        }else if(e instanceof BindException)
        {
            logger.error("参数较验失败：" + e.getMessage(), e);
            String message="";
            List<ObjectError> errors= ((BindException) e).getBindingResult().getAllErrors();
            for(ObjectError item : errors)
            {
                message=message+item.getDefaultMessage()+",";
            }
            resp.setResultCode(ResultCode.ParameteErr);
            resp.setResultMsg(message);
        }
        else if(e instanceof CoreException)
        {
            logger.error("自定义错误信息:code:"+((CoreException) e).getErrCode()+" msg:"+((CoreException) e).getErrMessage(),e);
            resp.setResultCode(((CoreException) e).getErrCode());
            resp.setResultMsg(((CoreException) e).getErrMessage());
        }else if (e instanceof IllegalArgumentException || e instanceof  NumberFormatException || e instanceof MethodArgumentTypeMismatchException) {
            logger.error("参数类型错误:"+e.getMessage(), e);
            resp.setResultMsg("参数类型错误");
            resp.setResultCode(ResultCode.ParameteErr);
        } else if (e instanceof SQLException) {
            logger.error("数据库操作异常：" + e.getMessage(), e);
            resp.setResultMsg("数据库访问异常");
            resp.setResultCode(ResultCode.SqlErr);
        }
        else {
            logger.error("服务器异常：" + e.getMessage(), e);
            resp.setResultMsg("服务器异常");
            resp.setResultCode(ResultCode.ServerErr);
        }

        res.setContentType("application/json; charset=utf-8");
        res.setCharacterEncoding("utf-8");
        /* 允许跨域的主机地址 */
        res.setHeader("Access-Control-Allow-Origin", "*");
        /* 允许跨域的请求方法GET, POST, HEAD 等 */
        res.setHeader("Access-Control-Allow-Methods", "*");
        /* 重新预检验跨域的缓存时间 (s) */
        res.setHeader("Access-Control-Max-Age", "3600");
        /* 允许跨域的请求头 */
        res.setHeader("Access-Control-Allow-Headers", "*");
        /* 是否携带cookie */
        res.setHeader("Access-Control-Allow-Credentials", "true");

        return resp;
    }
}
