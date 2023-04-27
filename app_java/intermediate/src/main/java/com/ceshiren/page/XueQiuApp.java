package com.ceshiren.page;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

//雪球首页
public class XueQiuApp extends KeyWords {
    public XueQiuApp(AndroidDriver driver) {
        super(driver);
    }

    public XueQiuApp() {
        if (driver == null) {
            startApp();
        }
    }

    //1、打开app
    public XueQiuApp startApp() {
        if (driver == null) {
            DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
            desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
            desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "6.0.1");
            desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "mumu");//随便命名
            desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
            //打开app的时候的页面是哪个
            desiredCapabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.xueqiu.android");
            desiredCapabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, ".view.WelcomeActivityAlias");


            //在假设客户端退出并结束会话之前，Appium 将等待来自客户端的新命令多长时间（以秒为单位）
            desiredCapabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 60);
            //保留历史数据
            desiredCapabilities.setCapability(MobileCapabilityType.NO_RESET, true);

            try {
                URL remoteUrl = new URL("http://127.0.0.1:4723/wd/hub");
                //打开app主界面
                driver = new AndroidDriver(remoteUrl, desiredCapabilities);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

            //隐式等待（全局等待）
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        }
        //打开雪球app
        return this;
    }

    //跳转到搜索页面
    /*public SearchPage toSearchPage() {
        //1.点击首页的搜索按钮
        driver.findElement(AppiumBy.id()).click();
        return new SearchPage(driver);
    }*/
}
