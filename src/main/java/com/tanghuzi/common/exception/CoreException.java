package com.tanghuzi.common.exception;

import com.tanghuzi.common.enums.ResultCode;

/**
 * 核心自定义异常类
 * @author: Liming
 * @date: 2018/11/22 1:15
 * @version 1.0
 **/
public class CoreException extends Exception {
    /**
     * 错误代码
     */
    private ResultCode errCode;
    /**
     * 错误信息
     */
    private String errMessage;

    /**
     * 构造函数
     * @param errCode
     * @param errMessage
     */
    public CoreException(ResultCode errCode, String errMessage)
    {
        super(errMessage);
        this.errCode=errCode;
        this.errMessage=errMessage;
    }

    /**
     * 获取异常代码
     * @return
     */
    public ResultCode getErrCode() {
        return errCode;
    }

    /**
     * 设置异常代码
     * @param errCode
     */
    public void setErrCode(ResultCode errCode) {
        this.errCode = errCode;
    }

    /**
     * 获取异常信息
     * @return
     */
    public String getErrMessage() {
        return errMessage;
    }

    /**
     * 设置异常信息
     * @param errMessage
     */
    public void setErrMessage(String errMessage) {
        this.errMessage = errMessage;
    }
}
