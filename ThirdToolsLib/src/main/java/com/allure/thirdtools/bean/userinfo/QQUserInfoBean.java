package com.allure.thirdtools.bean.userinfo;

import android.text.TextUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * 作者：luomin
 * 邮箱：asddavid@163.com
 */

public class QQUserInfoBean extends  BaseUserInfoBean implements Serializable {
    private String qZoneHeadImage;

    private String qZoneHeadImageLarge;

    public String getqZoneHeadImage() {
        return qZoneHeadImage;
    }

    public void setqZoneHeadImage(String qZoneHeadImage) {
        this.qZoneHeadImage = qZoneHeadImage;
    }

    public String getqZoneHeadImageLarge() {
        return qZoneHeadImageLarge;
    }

    public void setqZoneHeadImageLarge(String qZoneHeadImageLarge) {
        this.qZoneHeadImageLarge = qZoneHeadImageLarge;
    }

    public static QQUserInfoBean getUserInfo(String openId, JSONObject jsonObject) {
        QQUserInfoBean user = new QQUserInfoBean();

        try {
            user.setNickname(jsonObject.getString("nickname"));
            user.setOpenId(openId);
            user.setSex(TextUtils.equals("男", jsonObject.getString("gender")) ? 1 : 2);
            user.setHeadImageUrl(jsonObject.getString("figureurl_qq_1"));
            user.setHeadImageUrlLarge(jsonObject.getString("figureurl_qq_2"));
            user.setqZoneHeadImage(jsonObject.getString("figureurl_1"));
            user.setqZoneHeadImageLarge(jsonObject.getString("figureurl_2"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return user;
    }
}
