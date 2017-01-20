package com.allure.thirdtools.observer;

import com.allure.thirdtools.bean.LoginResult;
import com.allure.thirdtools.platform.LoginPlatform;
import com.allure.thirdtools.platform.PayPlatform;
import com.allure.thirdtools.platform.SharePlatform;

/**
 * 作者：luomin
 * 邮箱：asddavid@163.com
 */

public class LoginEvent {
    private static LoginEvent instance;
    private LoginObservable loginObservableble;

    public static LoginEvent getDefault() {
        if (instance == null) {
            synchronized (LoginEvent.class) {
                if (instance == null) {
                    instance = new LoginEvent();
                }
            }
        }
        return instance;
    }

    private LoginEvent() {
        loginObservableble = new LoginObservable();
    }

   
    public void register(Observer observer) {
        loginObservableble.addObserver(observer);
    }

    
    public void unregister(Observer observer) {
        loginObservableble.removeObserver(observer);
    }

    
    public void postSuccess(Object obj, LoginPlatform loginPlatform, LoginResult loginResult) {
        loginObservableble.successObservers(obj,loginPlatform,loginResult);
    }

    public void postFailed(Object obj, LoginPlatform loginPlatform) {
        loginObservableble.failedObservers(obj,loginPlatform);
    }

    public void postCancel(LoginPlatform loginPlatform) {
        loginObservableble.cancelObservers(loginPlatform);
    }

    public void shareSuccess(SharePlatform sharePlatform) {
        loginObservableble.shareSuccess(sharePlatform);

    }
    public void shareFailed(SharePlatform sharePlatform) {
        loginObservableble.shareFailed(sharePlatform);

    }

    public void paySuccess(PayPlatform payPlatform) {
        loginObservableble.paySuccess(payPlatform);
    }
    public void payFailed(PayPlatform payPlatform) {
        loginObservableble.payFailed(payPlatform);
    }
}
