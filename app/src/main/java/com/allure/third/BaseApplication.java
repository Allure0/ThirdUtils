package com.allure.third;

import android.app.Application;

import com.allure.thirdtools.PlatformManager;
import com.allure.thirdtools.platform.PlatformConfig;

/**
 * 作者：luomin
 * 邮箱：asddavid@163.com
 */

public class BaseApplication extends Application {
    private static BaseApplication instance;

    public static BaseApplication getInstance() {
        return instance;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        PlatformConfig platformConfig = PlatformConfig.getInstance()
                .setQqId("xxx")
                .setWeChatId("xxxx")
                .setWeChatSecret("");//如果是服务处理token和code不需要传此参数

        PlatformManager.getInstance()
                .setPlatformConfig(platformConfig)
                .initQQ(this)
                .initWx(this);
    }


}
