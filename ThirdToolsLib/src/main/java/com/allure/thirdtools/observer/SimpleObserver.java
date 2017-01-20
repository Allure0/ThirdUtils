package com.allure.thirdtools.observer;

import com.allure.thirdtools.bean.LoginResult;
import com.allure.thirdtools.platform.LoginPlatform;
import com.allure.thirdtools.platform.PayPlatform;
import com.allure.thirdtools.platform.SharePlatform;

/**
 * 作者：luomin
 * 邮箱：asddavid@163.com
 */

public  class SimpleObserver implements Observer {
    @Override
    public void thirdLoginSuccess(Object obj, LoginPlatform platform, LoginResult loginResult) {

    }

    @Override
    public void thirdLoginFailed(Object object, LoginPlatform loginPlatform) {

    }

    @Override
    public void thirdLoginCancel(LoginPlatform loginPlatform) {

    }

    @Override
    public void shareSuccess(SharePlatform sharePlatform) {

    }

    @Override
    public void shareFailed(SharePlatform sharePlatform) {

    }

    @Override
    public void paySuccess(PayPlatform payPlatform) {

    }

    @Override
    public void payFailed(PayPlatform payPlatform) {

    }
}
