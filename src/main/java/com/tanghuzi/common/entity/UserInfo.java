package com.tanghuzi.common.entity;

/**
 *  用户基本信息
 * @version 1.0
 * @author: Liming
 * @date: 2019/2/2 0:00
 **/
public class UserInfo {
    //用户Id
    private String userId;
    //微信登陆openId
    private String openId;
    //呢称
    private String nickName;
    //头像地址
    private String headImg;

    /**
     * 获取用户头像地址
     * @return
     */
    public String getHeadImg() {
        return headImg;
    }

    /**
     * 设置用户头像地址
     * @param headImg
     */
    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    /**
     * 获取用户呢称
     * @return
     */
    public String getNickName() {
        return nickName;
    }

    /**
     * 设置用户呢称
     * @param nickName
     */
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    /**
     * 获取用户Id
     * @return
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设置用户Id
     * @param userId
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }
}
