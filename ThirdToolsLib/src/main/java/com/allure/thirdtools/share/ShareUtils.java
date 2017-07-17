package com.allure.thirdtools.share;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import com.allure.thirdtools.PlatformManager;
import com.tencent.mm.sdk.modelmsg.SendMessageToWX;
import com.tencent.mm.sdk.modelmsg.WXImageObject;
import com.tencent.mm.sdk.modelmsg.WXMediaMessage;
import com.tencent.mm.sdk.modelmsg.WXTextObject;
import com.tencent.mm.sdk.modelmsg.WXWebpageObject;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.tauth.Tencent;

/**
 * 作者：luomin
 * 邮箱：asddavid@163.com
 */
public class ShareUtils {
    private static ShareUtils shareUtils;
    private String shareContent, shareTitle, shareUrl, picUrl;
    private Context context;
    private Bitmap shareBitmap;
    public static final int WECHAT_FRIEND = SendMessageToWX.Req.WXSceneSession;
    public static final int WECHAT_CIRCLE = SendMessageToWX.Req.WXSceneTimeline;

    private ShareUtils(Context context) {
        this.context = context;
    }

    public static ShareUtils initShareUtils(Context context) {
        if (shareUtils == null)
            shareUtils = new ShareUtils(context);
        return shareUtils;
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

    /**
     * 分享到微信朋友圈或者好友
     *
     * @param context
     * @param flag    0是朋友圈，1是好友
     */
    public void shareToWX(Context context, final int flag) {
        IWXAPI api = PlatformManager.getInstance().getIwxApi();
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
            return;
        }
    }


    /**
     * 文字分享
     *
     * @param shareText
     * @param flag      0是朋友圈，1是好友
     */
    public void shareWxText(String shareText, int flag) {
        if (isWxInstall()) {
            WXTextObject textObj = new WXTextObject();
            textObj.text = shareText;

            WXMediaMessage msg = new WXMediaMessage();
            msg.mediaObject = textObj;
            // msg.title = "Will be ignored";
            msg.description = shareText;

            SendMessageToWX.Req req = new SendMessageToWX.Req();
            req.transaction = buildTransaction("text");
            req.message = msg;
            req.scene = flag;

            IWXAPI api = PlatformManager.getInstance().getIwxApi();
            api.sendReq(req);
            return;
        }

    }

    /**
     * 微信图片分享
     *
     * @param bmp
     * @param flag 0是朋友圈，1是好友
     */
    public void shareWxPic(Bitmap bmp, final int flag) {

        if (isWxInstall()) {
            WXImageObject imgObj = new WXImageObject(bmp);
            WXMediaMessage msg = new WXMediaMessage();
            msg.mediaObject = imgObj;
            Bitmap thumbBmp = Bitmap.createScaledBitmap(bmp, 120, 120, true);
            bmp.recycle();
            msg.thumbData = WxUtil.bmpToByteArray(thumbBmp, true);

            SendMessageToWX.Req req = new SendMessageToWX.Req();
            req.transaction = buildTransaction("img");
            req.message = msg;
            req.scene = flag;
            IWXAPI api = PlatformManager.getInstance().getIwxApi();
            api.sendReq(req);
            return;
        }

    }


    /**
     * 分享给QQ好友以及QQ空间
     *
     * @param context
     * @param appName
     */
    public void shareToQQ(Context context, String appName) {
        Tencent mTencent = null;
        mTencent = PlatformManager.getInstance().getTencent();
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


    public boolean isWxInstall() {
        if (PlatformManager.getInstance().getIwxApi().isWXAppSupportAPI()) {
            return true;
        } else {
            Toast.makeText(context, "请先安装微信客户端", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    private String buildTransaction(final String type) {
        return (type == null) ? String.valueOf(System.currentTimeMillis()) : type + System.currentTimeMillis();
    }
}
