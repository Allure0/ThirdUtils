package com.allure.thirdtools.platform;

/**
 * 作者：luomin
 * 邮箱：asddavid@163.com
 */

public class PlatformConfig {
    private  String qqId;
    private String weChatId;
    private String weChatSecret;



    public static PlatformConfig getInstance() {
        return new PlatformConfig();
    }
    public String getQqId() {
        return qqId;
    }

    public PlatformConfig setQqId(String qqId) {
        this.qqId = qqId;
        return this;
    }

    public String getWeChatId() {
        return weChatId;
    }

    public PlatformConfig setWeChatId(String weChatId) {
        this.weChatId = weChatId;
        return this;
    }

    public String getWeChatSecret() {
        return weChatSecret;
    }

    public PlatformConfig setWeChatSecret(String weChatSecret) {
        this.weChatSecret = weChatSecret;
        return this;
    }
}
