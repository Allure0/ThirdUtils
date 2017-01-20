package com.allure.thirdtools.listener;

import com.allure.thirdtools.pay.AliPayResult;

/**
 * 作者：luomin
 * 邮箱：asddavid@163.com
 */

public interface AliPayListener {
    void  aliPayResult(String string,AliPayResult payResult);
}
