package com.allure.thirdtools.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.allure.thirdtools.PlatformManager;
import com.allure.thirdtools.observer.LoginEvent;
import com.allure.thirdtools.platform.LoginPlatform;
import com.allure.thirdtools.platform.SharePlatform;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.modelmsg.SendAuth;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;

/**
 * 微信分享与登陆
 * 作者：luomin
 * 邮箱：asddavid@163.com
 */

public class WXEntryActivity extends Activity implements IWXAPIEventHandler {
    private static final String TAG = "WXEntryActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            PlatformManager.getInstance().getIwxApi().handleIntent(getIntent(),this);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.i(TAG, "onNewIntent");
        setIntent(intent);
        PlatformManager.getInstance().getIwxApi().handleIntent(getIntent(),this);
    }

    /**
     * 请求回调接口
     */
    @Override
    public void onReq(BaseReq req) {
        Log.i(TAG, "req");
    }

    /**
     * 请求响应回调接口
     */
    @Override
    public void onResp(BaseResp resp) {
        Log.i(TAG, "onResp");

        if (resp.getType() == 1) {//登陆
            SendAuth.Resp sendAuthResp = (SendAuth.Resp) resp;
            String code = sendAuthResp.code;
            if (resp.errCode == BaseResp.ErrCode.ERR_OK) {
                LoginEvent.getDefault().postSuccess(code,
                        LoginPlatform.PLATFORM_WECHAT,
                        null);
            } else if (resp.errCode == BaseResp.ErrCode.ERR_USER_CANCEL) {
                LoginEvent.getDefault().postCancel(LoginPlatform.PLATFORM_WECHAT);
            } else {
                LoginEvent.getDefault().postFailed(resp, LoginPlatform.PLATFORM_WECHAT);
            }
            finish();

        } else if (resp.getType() == 2) {//分享
            if (resp.errCode == BaseResp.ErrCode.ERR_OK) {
                LoginEvent.getDefault().shareSuccess(SharePlatform.PLATFORM_WECHAT);
            } else {
                LoginEvent.getDefault().shareFailed(SharePlatform.PLATFORM_WECHAT);
            }
            finish();
        }


    }


}
