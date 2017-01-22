package com.allure.third;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.allure.thirdtools.LoginManager;
import com.allure.thirdtools.PayManager;
import com.allure.thirdtools.bean.LoginResult;
import com.allure.thirdtools.bean.pay.WxPayBean;
import com.allure.thirdtools.listener.AliPayListener;
import com.allure.thirdtools.observer.LoginEvent;
import com.allure.thirdtools.observer.Observer;
import com.allure.thirdtools.observer.SimpleObserver;
import com.allure.thirdtools.pay.AliPayResult;
import com.allure.thirdtools.platform.LoginPlatform;
import com.allure.thirdtools.platform.PayPlatform;
import com.allure.thirdtools.platform.SharePlatform;
import com.allure.thirdtools.share.ShareUtils;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, Observer {
    private Button btnLoginQQ;
    private Button btnLoginWeChat;
    private Button btnWechatShare;
    private Button btnQQShare;
    private Button btnWechatPay;
    private Button btnAliPay;

    //分享相关属性
    String text = "微信分享纯文本"; // 用于分享的文字
    String url = "https://www.baidu.com/";// 用于分享的链接
    String picPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + "test.jpg";// 用于分享的本地图片
    final String imgUrl = "http://segmentfault.com/img/bVkIvr";// 用于分享的在线图片
    String musicUrl = "http://staff2.ustc.edu.cn/~wdw/softdown/index.asp/0042515_05.ANDY.mp3";// 用于分享在线音乐
    String videoUrl = "http://v.youku.com/v_show/id_XMjExOTcxNg==.html?f=1245977";// 用于分享的在线视频


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LoginEvent.getDefault().register(this);
//        LoginEvent.getDefault().register(simpleObserver);
        btnLoginQQ = (Button) findViewById(R.id.btn_qq);
        btnLoginWeChat = (Button) findViewById(R.id.btn_wechat);
        btnQQShare = (Button) findViewById(R.id.btn_qq_share);
        btnWechatShare = (Button) findViewById(R.id.btn_wechat_share);
        btnWechatPay = (Button) findViewById(R.id.btn_wechat_pay);
        btnAliPay = (Button) findViewById(R.id.btn_ali_pay);

        btnLoginQQ.setOnClickListener(this);
        btnLoginWeChat.setOnClickListener(this);
        btnWechatShare.setOnClickListener(this);
        btnQQShare.setOnClickListener(this);
        btnWechatPay.setOnClickListener(this);
        btnAliPay.setOnClickListener(this);


    }

    SimpleObserver simpleObserver = new SimpleObserver() {
        @Override
        public void shareFailed(SharePlatform sharePlatform) {
            super.shareFailed(sharePlatform);
        }
    };


    @Override
    protected void onDestroy() {
        super.onDestroy();
        LoginEvent.getDefault().unregister(this);
//        LoginEvent.getDefault().unregister(simpleObserver);

    }


    @Override
    public void thirdLoginSuccess(Object obj, LoginPlatform loginPlatform, LoginResult loginResult) {
        switch (loginPlatform) {
            case PLATFORM_QQ:
                Toast.makeText(this, "token:" + loginResult.getBaseTokenBean().getAccessToken()
                        + "\n" + "昵称:" + loginResult.getBaseUserInfoBean().getNickname(), Toast.LENGTH_SHORT).show();
                Log.e("token", loginResult.getBaseTokenBean().getAccessToken());
                Log.e("用户信息", loginResult.getBaseUserInfoBean().getNickname());
                break;

            case PLATFORM_WECHAT:
                /**
                 * 此处只返回了Code,loginResultw为null
                 * 得到Code之后调用https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code
                 *  获取到token和openID。之后再调用https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID 获取用户个人信息
                 *  微信开发文档：https://open.weixin.qq.com/cgi-bin/showdocument?action=dir_list&t=resource/res_list&verify=1&id=open1419317853&token=&lang=zh_CN
                 */
                Toast.makeText(MainActivity.this, "获取的Code:" + obj.toString(), Toast.LENGTH_SHORT).show();

                break;
        }


    }

    @Override
    public void thirdLoginFailed(Object object, LoginPlatform loginPlatform) {
        Toast.makeText(this, "登陆失败了", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void thirdLoginCancel(LoginPlatform loginPlatform) {
        Toast.makeText(this, "登陆取消了", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void shareSuccess(SharePlatform sharePlatform) {
        Toast.makeText(this, "分享成功", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void shareFailed(SharePlatform sharePlatform) {
        Toast.makeText(this, "分享失败", Toast.LENGTH_SHORT).show();

    }

    //支付宝暂时未加入观察者模式观察,直接使用回调处理
    @Override
    public void paySuccess(PayPlatform payPlatform) {
        switch (payPlatform) {
            case PLATFORM_ALI:
                Toast.makeText(this, "支付宝成功", Toast.LENGTH_SHORT).show();
                break;
            case PLATFORM_WECHAT:
                Toast.makeText(this, "微信支付成功", Toast.LENGTH_SHORT).show();
                break;
        }

    }

    @Override
    public void payFailed(PayPlatform payPlatform) {
        switch (payPlatform) {
            case PLATFORM_ALI:
                Toast.makeText(this, "支付宝失败", Toast.LENGTH_SHORT).show();
                break;
            case PLATFORM_WECHAT:
                Toast.makeText(this, "微信支付失败", Toast.LENGTH_SHORT).show();
                break;
        }
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_qq://QQ登陆
                LoginManager
                        .with(MainActivity.this)
//                        .qqId(QQ_ID)
                        .thirdLogin(LoginPlatform.PLATFORM_QQ);
                break;
            case R.id.btn_wechat://微信登陆
                LoginManager
                        .with(MainActivity.this)
//                        .weChatId(WECHAT_ID)
                        .thirdLogin(LoginPlatform.PLATFORM_WECHAT);
                break;

            case R.id.btn_wechat_share:
                ShareUtils.initShareUtils(
                        BaseApplication.getInstance(), text, text, imgUrl, url,
                        BitmapFactory.decodeResource(BaseApplication.getInstance().getResources(),
                                R.drawable.ic_launcher)
                ).shareToWX(MainActivity.this, ShareUtils.WECHAT_CIRCLE);
                break;
            case R.id.btn_qq_share://QQ分享
                ShareUtils.initShareUtils(
                        BaseApplication.getInstance(), text, text, imgUrl, url,
                        BitmapFactory.decodeResource(BaseApplication.getInstance().getResources(),
                                R.drawable.ic_launcher)
                ).shareToQQ(MainActivity.this, "测试啦");
                break;
            case R.id.btn_wechat_pay://微信支付
                WxPayBean wxPayBean = new WxPayBean();
//                wxPayBean.setAppId(map.get("appid"));
//                wxPayBean.setPartnerId(map.get("partnerid"));
//                wxPayBean.setPrepayId(map.get("prepayid"));
//                wxPayBean.setPackageValue(map.get("package"));
//                wxPayBean.setNoncestr(map.get("noncestr"));
//                wxPayBean.setTimestamp(map.get("timestamp"));
//                wxPayBean.setSign(map.get("sign"));
                PayManager.with(MainActivity.this)
                        .wxPay(wxPayBean);

                break;
            case R.id.btn_ali_pay://支付宝支付
                String ali_orderinfo = "xxxxxx";
                PayManager.with(MainActivity.this)
                        .aliPay(ali_orderinfo, new AliPayListener() {
                            @Override
                            public void aliPayResult(String string, AliPayResult payResult) {
                                Log.e("支付状态", string);
                                Log.e("支付状态", payResult.getResultStatus());
                            }
                        });


                break;

        }
    }
}
