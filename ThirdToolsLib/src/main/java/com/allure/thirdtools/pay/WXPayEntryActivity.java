package com.allure.thirdtools.pay;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.allure.thirdtools.login.WxApiCreate;
import com.allure.thirdtools.observer.LoginEvent;
import com.allure.thirdtools.platform.PayPlatform;
import com.tencent.mm.sdk.constants.ConstantsAPI;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;

/**
 * 作者：luomin
 * 邮箱：asddavid@163.com
 */

public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            WxApiCreate.getInstance().getWxApi(WXPayEntryActivity.this).handleIntent(getIntent(), this);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.e("WXPayEntryActivity", "onNewIntent");
        setIntent(intent);
        WxApiCreate.getInstance().getWxApi(WXPayEntryActivity.this).handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq baseReq) {

    }

    @Override
    public void onResp(BaseResp baseResp) {
        Log.i("TAG", baseResp.getType() + "\n" + baseResp.errStr + "\n" + baseResp.errCode);
        if (baseResp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
            Log.e("resp",baseResp.errCode+"");
            if (baseResp.errCode == 0) {
                LoginEvent.getDefault().paySuccess(PayPlatform.PLATFORM_WECHAT);
                WXPayEntryActivity.this.finish();
            } else {
                LoginEvent.getDefault().payFailed(PayPlatform.PLATFORM_WECHAT);
                WXPayEntryActivity.this.finish();

            }
        }
    }
}
