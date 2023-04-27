package com.ceshiren.sample;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import static java.lang.Thread.sleep;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class XueQiuTest {
    private static AndroidDriver driver;

    @BeforeAll
    public static void setUp() {
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

    @Test
    public void test() throws InterruptedException {
        //显式等待，元素可点击时点击搜索框
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(AppiumBy.id("com.xueqiu.android:id/tv_search")
                )).click();

        //查找新页面输入框
        WebElement el1 = driver.findElement(AppiumBy.id("com.xueqiu.android:id/search_input_text"));

        //获取当前元素文本内容
        String text = el1.getText();
        System.out.println("当前文本内容" + text);
        //当前元素是否可见
        boolean displayed = el1.isDisplayed();
        System.out.println("元素是否可见" + displayed);

        //当前元素的resourceId
        //com.xueqiu.android:id/search_input_text
        String resourceId = el1.getAttribute("resource-id");
        System.out.println("resourceId:" + resourceId);
        //是否可点击
        String clickable = el1.getAttribute("long-clickable");
        System.out.println("clickable:" + clickable);
        //是否可用
        Boolean enabled = el1.isEnabled();
        System.out.println("元素是否可用" + enabled);
        //获取元素左上角的位置坐标
        Point location = el1.getLocation();
        System.out.println("元素左上角位置：" + location);

        //获取元素的宽度和高度
        Dimension size = el1.getSize();
        System.out.println("size:" + location);
        //获取中心点
        int startX = location.getX();
        int startY = location.getY();
        int midD = size.getWidth() / 2;
        int midH = size.getHeight() / 2;
        int middlerX = startX + midD;
        int middlerY = startY + midH;
        System.out.println("中心点x：" + middlerX);
        System.out.println("中心点y：" + middlerY);
        //清楚输入框并输入"alibaba"
        el1.clear();
        el1.sendKeys("alibaba");

        WebElement el2 = driver.findElement(AppiumBy.id("com.xueqiu.android:id/name"));
        String text2 = el2.getText();
        System.out.println(text2.equals("阿里巴巴") ? "搜索成功" : "搜索失败");
        assertTrue(el2.isDisplayed());
        sleep(3000);

    }

    @AfterAll
    public static void tearDown() {
        //退出driver
        driver.quit();
        System.out.println("退出app");
    }
}
