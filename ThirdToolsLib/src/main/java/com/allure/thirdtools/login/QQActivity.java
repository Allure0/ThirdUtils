package com.allure.thirdtools.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.allure.thirdtools.bean.LoginResult;
import com.allure.thirdtools.bean.token.QQTokenBean;
import com.allure.thirdtools.bean.userinfo.QQUserInfoBean;
import com.allure.thirdtools.observer.LoginEvent;
import com.allure.thirdtools.platform.LoginPlatform;
import com.tencent.connect.UserInfo;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.json.JSONObject;

/**
 * 作者：luomin
 * 邮箱：asddavid@163.com
 */

public class QQActivity extends AppCompatActivity {

    private Tencent mTencent;
    private MyListener mListener;
    private String qqId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        qqId = this.getIntent().getExtras().getString("qqId");
        mListener = new MyListener();
        if (mTencent == null) {
            mTencent = Tencent.createInstance(qqId, this);
        }
        QQLogin();
    }


    // 实现登录成功与否的接口
    private class MyListener implements IUiListener {

        @Override
        public void onComplete(final Object object) { //登录成功

            //获取token和openId
            final QQTokenBean qqTokenBean = QQTokenBean.getToken(object);
            mTencent.setOpenId(qqTokenBean.getOpenId());
            mTencent.setAccessToken(qqTokenBean.getAccessToken(), qqTokenBean.getExpires_in());
            //获取用户信息
            getUserInfo(object,qqTokenBean);



        }

        @Override
        public void onError(UiError uiError) {  //登录失败
            LoginEvent.getDefault().postFailed(uiError, LoginPlatform.PLATFORM_QQ);
            QQActivity.this. finish();
        }

        @Override
        public void onCancel() {                //取消登录
            LoginEvent.getDefault().postCancel(LoginPlatform.PLATFORM_QQ);
            QQActivity.this. finish();

        }
    }

    private void getUserInfo(final Object object, final QQTokenBean qqTokenBean) {
        if (mTencent != null && mTencent.isSessionValid()) {
            UserInfo userInfo = new UserInfo(QQActivity.this, mTencent.getQQToken());
            userInfo.getUserInfo(new IUiListener() {
                @Override
                public void onComplete(Object o) {
                    JSONObject jb = (JSONObject) o;
                    LoginResult loginResult = new LoginResult(QQTokenBean.getToken(object),
                            QQUserInfoBean.getUserInfo(qqTokenBean.getOpenId(), jb));

                    LoginEvent.getDefault().postSuccess(object.toString(),
                            LoginPlatform.PLATFORM_QQ,
                            loginResult);
                    QQActivity.this. finish();
                    finish();
                }

                @Override
                public void onError(UiError uiError) {
                    finish();
                }

                @Override
                public void onCancel() {
                    finish();
                }
            });
        }
    }


    private void QQLogin() {
//        if (!mTencent.isSessionValid()) {
            mTencent.login(this, "allure", mListener);
//        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mTencent.onActivityResultData(requestCode, resultCode, data, mListener);
    }
}
