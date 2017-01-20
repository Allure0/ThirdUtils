package com.allure.thirdtools.bean;

import com.allure.thirdtools.bean.token.BaseTokenBean;
import com.allure.thirdtools.bean.userinfo.BaseUserInfoBean;

/**
 * 作者：luomin
 * 邮箱：asddavid@163.com
 */

public class LoginResult {

    private BaseTokenBean baseTokenBean;
    private BaseUserInfoBean baseUserInfoBean;

    public LoginResult(BaseTokenBean baseTokenBean, BaseUserInfoBean baseUserInfoBean) {
        this.baseTokenBean = baseTokenBean;
        this.baseUserInfoBean = baseUserInfoBean;
    }

    public BaseTokenBean getBaseTokenBean() {
        return baseTokenBean;
    }

    public void setBaseTokenBean(BaseTokenBean baseTokenBean) {
        this.baseTokenBean = baseTokenBean;
    }

    public BaseUserInfoBean getBaseUserInfoBean() {
        return baseUserInfoBean;
    }

    public void setBaseUserInfoBean(BaseUserInfoBean baseUserInfoBean) {
        this.baseUserInfoBean = baseUserInfoBean;
    }
}
