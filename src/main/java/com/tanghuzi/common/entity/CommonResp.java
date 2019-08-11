package com.tanghuzi.common.entity;

import com.tanghuzi.common.enums.ResultCode;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 通用操作结果返回的基础类
 * @author: Liming
 * @date: 2018/11/22 0:26
 * @version 1.0
 * @param <T> 泛型
 */
public class CommonResp<T> {
    private ResultCode resultCode= ResultCode.Success;
    private String resultMsg="操作成功";
    private T data;
    /**
     * 获取操作结果代码
     * @return
     */
    @JsonIgnore
    public ResultCode getResultCode() {
        return resultCode;
    }

    /**
     * 获取操作结果代码(接口返回结果前端使用)
     * @return
     */
    public Integer getCode()
    {
        return resultCode.ordinal();
    }

    public void  setCode(Integer code)
    {
        this.resultCode=ResultCode.values()[code];
    }
    /**
     * 设置操作结果代码
     * @param resultCode
     */
    public void setResultCode(ResultCode resultCode) {
        this.resultCode = resultCode;
    }
    /**
     * 获取操作结果信息
     * @return
     */
    @JsonIgnore
    public String getResultMsg() {
        return resultMsg;
    }
    /**
     * 获取操作结果信息(接口返回结果前端使用)
     * @return
     */
    public String getMsg()
    {
        return  resultMsg;
    }

    public void setMsg(String msg){
        resultMsg=msg;
    }
    /**
     * 设置操作结果信息
     * @param resultMsg
     */
    public void setResultMsg(String resultMsg) {
        this.resultMsg =resultMsg;
    }
    /**
     * 获取操作结果数据对象
     * @return
     */
    public T getData() {
        return data;
    }
    /**
     * 设置操作结果数据对象
     * @param data
     */
    public void setData(T data) {
        this.data = data;
    }

}
