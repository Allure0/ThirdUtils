# ThirdUtils
集成第三方登陆分享(微信/QQ)，第三方支付（支付宝,微信,盛付通）,若要使用此项目，请将主项目的applicationId改为你的app，并且添加QQ_ID与WECHAT_ID测试
#使用方式

对于QQ需要在主项目build文件配置如下qq_id,将1105787445替换为你的QQ的APPKEY,参见主项目build.gradle
```
 manifestPlaceholders = [
                qq_id: "1105787445"
        ]
```
###效果图
![image](https://github.com/Allure0/ThirdUtils/blob/master/demo/guide_gif2.gif)
###QQ登陆
```
     LoginManager .with(MainActivity.this)
                  .qqId(QQ_ID)
                  .thirdLogin(LoginPlatform.PLATFORM_QQ);                  
```

###微信登陆
```
 LoginManager.with(MainActivity.this)
             .weChatId(WECHAT_ID)
             .thirdLogin(LoginPlatform.PLATFORM_WECHAT);
```

###微信分享
```
 ShareUtils.initShareUtils(
                        BaseApplication.getInstance(), text, text, imgUrl, url,
                        BitmapFactory.decodeResource(BaseApplication.getInstance().getResources(),
                                R.drawable.ic_launcher)
                ).shareToWX(MainActivity.this, WECHAT_ID, ShareUtils.WECHAT_CIRCLE);
```
### QQ分享
```
ShareUtils.initShareUtils(
                        BaseApplication.getInstance(), text, text, imgUrl, url,
                        BitmapFactory.decodeResource(BaseApplication.getInstance().getResources(),
                                R.drawable.ic_launcher)
                ).shareToQQ(MainActivity.this, QQ_ID, "测试啦");
```
###微信支付
```
  PayManager.with(MainActivity.this)
            .wxPay(WECHAT_ID, wxPayBean);
```
###支付宝支付
```
 PayManager.with(MainActivity.this)
           .aliPay(ali_orderinfo, new AliPayListener() {
                            @Override
                            public void aliPayResult(String string, AliPayResult payResult) {
                                Log.e("支付状态", string);
                                Log.e("支付状态", payResult.getResultStatus());
                            }
                        });
```


###观察者模式接受处理结果
```
 @Override
    public void thirdLoginSuccess(Object obj, LoginPlatform platform, LoginResult loginResult) {

    }

    @Override
    public void thirdLoginFailed(Object object, LoginPlatform loginPlatform) {

    }

    @Override
    public void thirdLoginCancel(LoginPlatform loginPlatform) {

    }

    @Override
    public void shareSuccess(SharePlatform sharePlatform) {

    }

    @Override
    public void shareFailed(SharePlatform sharePlatform) {

    }

    @Override
    public void paySuccess(PayPlatform payPlatform) {

    }

    @Override
    public void payFailed(PayPlatform payPlatform) {

    }
```
### 若有BUG或者疑问,请提交Issues。者QQ群:482906631
#License
Copyright 2016 Allure

Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
