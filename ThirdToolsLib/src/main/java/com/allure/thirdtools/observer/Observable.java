package com.allure.thirdtools.observer;

import com.allure.thirdtools.bean.LoginResult;
import com.allure.thirdtools.platform.LoginPlatform;
import com.allure.thirdtools.platform.PayPlatform;
import com.allure.thirdtools.platform.SharePlatform;

/**
 * 作者：luomin
 * 邮箱：asddavid@163.com
 */

public interface Observable {

    void addObserver(Observer observer);


    void removeObserver(Observer observer);


    public abstract  void successObservers(Object obj, LoginPlatform loginPlatform, LoginResult loginResult);

    public abstract  void failedObservers(Object obj,LoginPlatform loginPlatform);

    public abstract  void cancelObservers(LoginPlatform loginPlatform);

    public abstract void shareSuccess(SharePlatform sharePlatform);
    public abstract void shareFailed(SharePlatform sharePlatform);


    public abstract void paySuccess(PayPlatform payPlatform);

    public abstract void payFailed(PayPlatform payPlatform);

}