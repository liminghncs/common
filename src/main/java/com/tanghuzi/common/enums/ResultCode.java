package com.tanghuzi.common.enums;
/**
 * 通用操作结果类型枚举
 * @author: Liming
 * @date: 2018/11/22 0:26
 * @version 1.0
 */
public enum ResultCode {
    /**
     * 操作失败(0)
     */
    Fail,
    /**
     * 操作成功(1)
     */
    Success ,
    /**
     * 服务器异常(2)
     */
    ServerErr,
    /**
     * 传入的参数错误(3)
     */
    ParameteErr ,
    /**
     * 无权限调用此接口(4)
     */
    NoAuth ,
    /**
     * 数据库操作异常(5)
     */
    SqlErr,
    /**
     * 其他错误在ResultMsg中描述错误类型及原因(6)
     */
    Other
}
