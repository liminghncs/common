package com.tanghuzi.common.security;

import com.tanghuzi.common.config.CoreConfig;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.UnsupportedEncodingException;

/**
 * MD5加密
 * @author: Liming
 * @date: 2018/12/15 21:46
 * @version 1.0
 **/
public class MD5 {
    /**
     * MD5加密字符串
     * @param text 需要加密的字符串
     * @param input_charset 编码格式
     * @return 加密结果
     */
    public static String encrypt(String text, String input_charset) {
        return DigestUtils.md5Hex(getContentBytes(text, input_charset));
    }

    /**
     * MD5加密字符串
     * @param text 需要加密的字符串
     * @return 加密结果
     */
    public static String encrypt(String text)
    {
        return DigestUtils.md5Hex(getContentBytes(text, CoreConfig.INPUT_CHARSET));
    }

    private static byte[] getContentBytes(String content, String charset) {
        if (charset == null || "".equals(charset)) {
            return content.getBytes();
        }
        try {
            return content.getBytes(charset);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("MD5签名过程中出现错误,指定的编码集不对,您目前指定的编码集是:" + charset);
        }
    }
}
