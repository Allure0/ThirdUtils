package com.allure.third;

import android.app.Application;

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
    }



}
