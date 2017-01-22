package com.allure.thirdtools;

import android.content.Context;

import com.allure.thirdtools.platform.PlatformConfig;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.tencent.tauth.Tencent;

/**
 * 作者：luomin
 * 邮箱：asddavid@163.com
 */

public class PlatformManager {

    private IWXAPI iwxapi;
    private Tencent tencent;

    public static PlatformConfig platformConfig;

    private static class SingletonHolder {
        private static final PlatformManager INSTANCE = new PlatformManager();
    }

    public static final PlatformManager getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public PlatformManager setPlatformConfig(PlatformConfig config) {
        platformConfig = config;
        return this;
    }


    public IWXAPI getIwxApi() {
        return iwxapi;
    }

    public PlatformManager initWx(Context mContext) {

        if (null == iwxapi) {
            iwxapi = WXAPIFactory.createWXAPI(mContext.getApplicationContext(), platformConfig.getWeChatId(), true);
            iwxapi.registerApp(platformConfig.getWeChatId());
        }
        return this;
    }

    public Tencent getTencent() {
        return tencent;
    }

    public PlatformManager initQQ(Context mContext) {
        if (tencent == null) {
            tencent = Tencent.createInstance(platformConfig.getQqId(), mContext.getApplicationContext());
        }
        return this;
    }
}
