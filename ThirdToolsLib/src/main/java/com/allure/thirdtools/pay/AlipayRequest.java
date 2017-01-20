package com.allure.thirdtools.pay;

import android.app.Activity;

import com.alipay.sdk.app.PayTask;

/**
 * 作者：luomin
 * 邮箱：asddavid@163.com
 */
public class AlipayRequest {

    public static void StartAlipay(final Activity activity, final String payInfo, final AliPayCallback payCallback){
        // 必须异步调用
       new Thread(new Runnable() {
            @Override
            public void run() {
                // 构造PayTask 对象
                final PayTask alipay = new PayTask(activity);
                // 调用支付接口，获取支付结果
                 payCallback.payResult(alipay.pay(payInfo,true));
            }
        }).start();
    }
}
