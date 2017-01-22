package com.allure.thirdtools;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import com.allure.thirdtools.login.QQActivity;
import com.allure.thirdtools.platform.LoginPlatform;
import com.allure.thirdtools.share.ShareUtils;
import com.tencent.mm.sdk.modelmsg.SendAuth;

/**
 * 作者：luomin
 * 邮箱：asddavid@163.com
 */

public class LoginManager {

    private Object mContext;


    public LoginManager(Object object) {
        this.mContext = object;
    }


    public static LoginManager with(Context context) {
        return new LoginManager(context);
    }

    public static LoginManager with(Activity activity) {
        return new LoginManager(activity);
    }

    public static LoginManager with(Fragment fragmentV4) {
        return new LoginManager(fragmentV4);
    }


    public void thirdLogin(LoginPlatform platform) {
        switch (platform) {
            case PLATFORM_QQ:
                Intent intent = new Intent(ShareUtils.getActivtyContext(mContext), QQActivity.class);
                ShareUtils.getActivtyContext(mContext).startActivity(
                        intent);
                break;
            case PLATFORM_WECHAT:
                SendAuth.Req req;
                req = new SendAuth.Req();
                req.scope = "snsapi_userinfo";
                req.state = "login_state";
                PlatformManager.getInstance().getIwxApi().sendReq(req);
                break;
        }

    }


}
