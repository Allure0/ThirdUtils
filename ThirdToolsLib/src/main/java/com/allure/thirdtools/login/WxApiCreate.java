package com.allure.thirdtools.login;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.allure.thirdtools.share.ShareUtils;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

/**
 * 作者：luomin
 * 邮箱：asddavid@163.com
 */

public class WxApiCreate {
    //微信
    private IWXAPI api;
    private String wxId;

    private static WxApiCreate instance;

    public static WxApiCreate getInstance() {
        if (instance == null) {
            synchronized (WxApiCreate.class) {
                if (instance == null) {
                    instance = new WxApiCreate();
                }
            }
        }
        return instance;
    }

    public String getWxId() {
        return wxId;
    }

    private WxApiCreate() {
    }
    public WxApiCreate setWxId(String wxId){
            this.wxId=wxId;
        return this;
    }


    /**
     * 1105787445
     * @return
     */
    public IWXAPI getWxApi(Context mContext) {
        if(TextUtils.isEmpty(getWxId())){
            Log.e("WxApiCreate","请先调用setWxId方法设置wxId");
            return  null;
        }
        if (null == api) {
            api = WXAPIFactory.createWXAPI(ShareUtils.getActiivtyContext(mContext), wxId, true);
            api.registerApp(wxId);
        }
        return api;
    }

}
