package com.allure.thirdtools.observer;

import com.allure.thirdtools.bean.LoginResult;
import com.allure.thirdtools.platform.LoginPlatform;
import com.allure.thirdtools.platform.PayPlatform;
import com.allure.thirdtools.platform.SharePlatform;

/**
 * 作者：luomin
 * 邮箱：asddavid@163.com
 */

public interface Observer {

    public abstract void thirdLoginSuccess(Object obj, LoginPlatform platform, LoginResult loginResult);

    public abstract void thirdLoginFailed(Object object, LoginPlatform loginPlatform);

    public abstract void thirdLoginCancel(LoginPlatform loginPlatform);

    public abstract void shareSuccess(SharePlatform sharePlatform);

    public abstract void shareFailed(SharePlatform sharePlatform);

    public abstract void paySuccess(PayPlatform payPlatform);

    public abstract void payFailed(PayPlatform payPlatform);

}
