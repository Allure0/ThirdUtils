package com.allure.thirdtools.bean.token;

/**
 * 作者：luomin
 * 邮箱：asddavid@163.com
 */

public class BaseTokenBean {
    private String openId;
    private String accessToken;



    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

}
