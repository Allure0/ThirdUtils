package com.allure.thirdtools.share;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.allure.thirdtools.PlatformManager;
import com.tencent.mm.sdk.modelmsg.SendMessageToWX;
import com.tencent.mm.sdk.modelmsg.WXMediaMessage;
import com.tencent.mm.sdk.modelmsg.WXWebpageObject;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.tauth.Tencent;

/**
 * Created by Administrator on 2016/6/6.
 */
public class ShareUtils {
    //com.google.android.gms
//    public final static String googlePlayPackName = "com.android.play.services";
    private static ShareUtils shareUtils;
    private String shareContent, shareTitle, shareUrl, picUrl;
    private Context context;
    private Bitmap shareBitmap;
    public static final int WECHAT_FRIEND = SendMessageToWX.Req.WXSceneSession;
    public static final int WECHAT_CIRCLE = SendMessageToWX.Req.WXSceneTimeline;

    private ShareUtils(Context context) {
        this.context = context;
    }


    /**
     * @param context
     * @param shareTitle
     * @param shareContent
     * @param picUrl
     * @param shareUrl
     * @param shareBitmap
     * @return
     */
    public static ShareUtils initShareUtils(Context context, String shareTitle, String shareContent, String picUrl, String shareUrl, Bitmap shareBitmap) {
        if (shareUtils == null)
            shareUtils = new ShareUtils(context);
        shareUtils.shareUrl = shareUrl;
        shareUtils.shareTitle = shareTitle;
        shareUtils.shareContent = shareContent;
        shareUtils.shareBitmap = shareBitmap;
        shareUtils.picUrl = picUrl;
        return shareUtils;
    }

    // 分享到微信朋友圈或者好友 0是朋友圈，1是好友
    public void shareToWX(Context context, final int flag) {
//        IWXAPI api = WxApiCreate.getInstance().setWxId(wxId).getWxApi(context);
        IWXAPI api= PlatformManager.getInstance().getIwxApi();
        if (api.isWXAppSupportAPI()) {
            WXWebpageObject webpage = new WXWebpageObject();
            webpage.webpageUrl = shareUrl;
            WXMediaMessage msg = new WXMediaMessage(webpage);

            msg.title = shareTitle;
            msg.description = shareContent;
            msg.setThumbImage(shareBitmap);

            SendMessageToWX.Req req = new SendMessageToWX.Req();
            req.transaction = String.valueOf(System.currentTimeMillis());
            req.message = msg;
            req.scene = flag;

            api.sendReq(req);
        } else {

        }


    }

    // 分享给QQ好友以及QQ空间
    public void shareToQQ(Context context,String appName) {
        Tencent mTencent = null;
        mTencent=PlatformManager.getInstance().getTencent();
        Bundle bundle = new Bundle();
        bundle.putString("title", shareTitle);// 标题
        bundle.putString("imageUrl", picUrl);
        bundle.putString("targetUrl", shareUrl);
        bundle.putString("summary", shareContent);// 内容
        bundle.putString("appName", appName);
        mTencent.shareToQQ((Activity) context, bundle, null);
    }

    public static Activity getActivtyContext(Object object) {
        if (object instanceof Activity) {
            return (Activity) object;
        } else if (object instanceof Fragment) {
            return ((Fragment) object).getActivity();
        } else if (object instanceof android.app.Fragment) {
            return ((Fragment) object).getActivity();
        }
        return null;
    }

}
