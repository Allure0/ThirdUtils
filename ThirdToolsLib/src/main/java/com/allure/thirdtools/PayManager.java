package com.allure.thirdtools;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;

import com.allure.thirdtools.bean.pay.WxPayBean;
import com.allure.thirdtools.listener.AliPayListener;
import com.allure.thirdtools.pay.AliPayCallback;
import com.allure.thirdtools.pay.AliPayResult;
import com.allure.thirdtools.pay.AlipayRequest;
import com.allure.thirdtools.share.ShareUtils;
import com.tencent.mm.sdk.modelpay.PayReq;

/**
 * 作者：luomin
 * 邮箱：asddavid@163.com
 */

public class PayManager {
    private Object mContext;

    public PayManager(Object objcet) {
        this.mContext = objcet;
    }

    public static PayManager with(Context context) {
        return new PayManager(context);
    }

    public static PayManager with(Activity activity) {
        return new PayManager(activity);
    }

    public static PayManager with(Fragment fragmentV4) {
        return new PayManager(fragmentV4);
    }

    public void wxPay( WxPayBean wxPayBean) {
        PayReq payReq = new PayReq();
        payReq.appId = wxPayBean.getAppId();
        payReq.partnerId = wxPayBean.getPartnerId();
        payReq.prepayId = wxPayBean.getPrepayId();
        payReq.packageValue = wxPayBean.getPackageValue();
        payReq.nonceStr = wxPayBean.getNoncestr();
        payReq.timeStamp = wxPayBean.getTimestamp();
        payReq.sign = wxPayBean.getSign();
        PlatformManager.getInstance().getIwxApi().sendReq(payReq);
    }


    public void aliPay(String orderInfo, final AliPayListener aliPayListener) {
        AlipayRequest.StartAlipay(ShareUtils.getActivtyContext(mContext), orderInfo, new AliPayCallback() {
            @Override
            public void payResult(String result) {
                AliPayResult payResult = new AliPayResult(result);
                aliPayListener.aliPayResult(result, payResult);
            }
        });

    }
}
