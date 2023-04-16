package com.ceshiren.sample;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import static io.appium.java_client.MobileCommand.LAUNCH_APP;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SampleTest {
    private static AndroidDriver driver;

    @BeforeAll
    public static void setUp() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "6.0.1");
        desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "mumu");
        desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
        //打开app的页面
        desiredCapabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, ".MainActivity");
        desiredCapabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "io.cloudgrey.the_app");
        //desiredCapabilities.setCapability("appium:ensureWebviewsHavePages", true);
        //desiredCapabilities.setCapability("appium:nativeWebScreenshot", true);
        //在假设客户端退出并结束会话之前，Appium 将等待来自客户端的新命令多长时间（以秒为单位）
        desiredCapabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 10);
        //desiredCapabilities.setCapability("appium:connectHardwareKeyboard", true);
        //添加参数保留历史数据
        desiredCapabilities.setCapability(MobileCapabilityType.NO_RESET, true);

        URL remoteUrl = new URL("http://127.0.0.1:4723/wd/hub");
        //1、拿到手机，打开app主界面
        driver = new AndroidDriver(remoteUrl, desiredCapabilities);

        //隐式等待
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
    }

    @Test
    public void sampleTest() {
        //查找 echo box元素
        WebElement el1 = driver.findElement(AppiumBy.xpath("//android.view.ViewGroup[@content-desc=\"Echo Box\"]/android.widget.TextView[1]"));
        //点击echo box元素
        el1.click();
        //定位元素：say something new
        WebElement el2 = driver.findElement(AppiumBy.accessibilityId("messageInput"));
        el2.clear();
        el2.sendKeys("hogwarts");
        //定位save元素
        WebElement el3 = driver.findElement(AppiumBy.xpath("//android.view.ViewGroup[@content-desc=\"messageSaveBtn\"]/android.widget.TextView"));
        el3.click();
        //查找app返回按钮元素
        WebElement el4 = driver.findElement(AppiumBy.className("android.widget.ImageButton"));
        el4.click();
        System.out.println("退出app");
        //热启动 打开app退出时的页面
        driver.execute(LAUNCH_APP);
        //获取the app元素定位
        WebElement element = driver.findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.TextView"));
        String text = element.getText();
        System.out.println("text:" + text);

        //添加断言
        assertEquals("The App", text, "对应页面没有返回值");
    }


    @AfterAll
    public static void tearDown() {
        //最后退出driver
        driver.quit();
    }
}
