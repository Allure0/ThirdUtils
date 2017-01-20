package com.allure.thirdtools.bean.token;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * 作者：luomin
 * 邮箱：asddavid@163.com
 */

public class QQTokenBean extends BaseTokenBean implements Serializable {

    private String  expires_in;

    public String getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(String expires_in) {
        this.expires_in = expires_in;
    }

    public static QQTokenBean getToken(Object object)  {
        QQTokenBean token = new QQTokenBean();
        JSONObject jsonObject = (JSONObject) object;
        try {
            token.setAccessToken(jsonObject.getString("access_token"));
            token.setOpenId(jsonObject.getString("openid"));
            token.setExpires_in(jsonObject.getString("expires_in"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return token;
    }

}
