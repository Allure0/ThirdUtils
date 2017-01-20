package com.allure.thirdtools.observer;

import com.allure.thirdtools.bean.LoginResult;
import com.allure.thirdtools.platform.LoginPlatform;
import com.allure.thirdtools.platform.PayPlatform;
import com.allure.thirdtools.platform.SharePlatform;

import java.util.ArrayList;

/**
 * 作者：luomin
 * 邮箱：asddavid@163.com
 */

public class LoginObservable implements Observable {
    private ArrayList<Observer> observers;

    public  void returnNull(){
        if (observers == null || observers.size() <= 0) {
            return;
        }
    }
    @Override
    public void addObserver(Observer observer) {
        if (observers == null) {
            observers = new ArrayList<>();
        }
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        returnNull();
        observers.remove(observer);
    }

    @Override
    public void successObservers(Object obj, LoginPlatform loginPlatform, LoginResult loginResult) {
        returnNull();
        for (Observer observer : observers) {
            observer.thirdLoginSuccess(obj,loginPlatform,loginResult);
        }
    }

    @Override
    public void failedObservers(Object obj,LoginPlatform loginPlatform) {
        returnNull();
        for (Observer observer : observers) {
            observer.thirdLoginFailed(obj,loginPlatform);
        }
    }

    @Override
    public void cancelObservers(LoginPlatform loginPlatform) {
        returnNull();
        for (Observer observer : observers) {
            observer.thirdLoginCancel(loginPlatform);
        }
    }

    @Override
    public void shareSuccess(SharePlatform sharePlatform) {
        returnNull();
        for (Observer observer : observers) {
            observer.shareSuccess(sharePlatform);
        }
    }
    @Override
    public void shareFailed(SharePlatform sharePlatform) {
        returnNull();
        for (Observer observer : observers) {
            observer.shareFailed(sharePlatform);
        }
    }

    @Override
    public void paySuccess(PayPlatform payPlatform) {
        returnNull();
        for (Observer observer : observers) {
            observer.paySuccess(payPlatform);
        }
    }

    @Override
    public void payFailed(PayPlatform payPlatform) {
        returnNull();
        for (Observer observer : observers) {
            observer.payFailed(payPlatform);
        }
    }


}
