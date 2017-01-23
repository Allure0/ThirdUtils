# ThirdUtils

集成第三方登陆分享(微信/QQ)，第三方支付（支付宝,微信,盛付通）
本Demo并不能直接使用，若要使用此项目，请将主项目的applicationId改为你的包名，signingConfigs配置签名配置
#使用方式
 - 主项目的gradle.properties里必须配置isCompile,默认为true


    | 属性 | 解释  |
    | :---------: | :------: |
    | true   | 使用libs里的第三包    |
    | false   | 自行选择功能与第三方jar包   |

 例:只需要使用支付宝的支付功能：
 主项目build放入最新的支付宝jar包:
 ```
 dependencies {
   ···
 compile files('libs/alipaySdk-20161222.jar')   
   ···
}
 ```
 gradle.properties配置：
 ```
  isCompile=false
 ```
 
对于QQ需要在主项目build文件配置如下qq_id,将1105787445替换为你的QQ的APPKEY,参见主项目build.gradle
```
 manifestPlaceholders = [
                qq_id: "1105787445"
        ]
```
###效果图
![image](https://github.com/Allure0/ThirdUtils/blob/master/demo/guide_gif2.gif)

###初始化配置
```
PlatformConfig platformConfig = PlatformConfig.getInstance()
                .setQqId("xxx")
                .setWeChatId("xxxx")
                .setWeChatSecret("");//如果是服务处理token和code不需要传此参数

        PlatformManager.getInstance()
                .setPlatformConfig(platformConfig)
                .initQQ(this)
                .initWx(this);
```
###QQ登陆
```
     LoginManager
                .with(MainActivity.this)
                .thirdLogin(LoginPlatform.PLATFORM_QQ);              
```

###微信登陆
```
 LoginManager.with(MainActivity.this)
             .thirdLogin(LoginPlatform.PLATFORM_WECHAT);
```

###微信分享
```
 ShareUtils.initShareUtils(
                        BaseApplication.getInstance(), text, text, imgUrl, url,
                        BitmapFactory.decodeResource(BaseApplication.getInstance().getResources(),
                                R.drawable.ic_launcher)
                ).shareToWX(MainActivity.this, ShareUtils.WECHAT_CIRCLE);
```
### QQ分享
```
ShareUtils.initShareUtils(
                        BaseApplication.getInstance(), text, text, imgUrl, url,
                        BitmapFactory.decodeResource(BaseApplication.getInstance().getResources(),
                                R.drawable.ic_launcher)
                ).shareToQQ(MainActivity.this, "测试啦");
```
###微信支付
```
  PayManager.with(MainActivity.this)
            .wxPay( wxPayBean);
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
Copyright 2017 Allure

Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
