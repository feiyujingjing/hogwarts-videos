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

public class ToastTest {
    private static AndroidDriver driver;

    @BeforeAll
    public static void setUp() {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "6.0.1");
        desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "mumu");//随便命名
        desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
        //打开app的时候的页面是哪个
        desiredCapabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "io.selendroid.testapp");
        desiredCapabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, ".HomeScreenActivity");


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
    public void test() {
        //点击Displays a Toast按钮
        driver.findElement(AppiumBy.id("io.selendroid.testapp:id/showToastButton")).click();
        //两种方式获取toast
        WebElement element = driver.findElement(AppiumBy.xpath("//*[@class=\"android.widget.Toast\"]"));
        WebElement el2 = driver.findElement(AppiumBy.xpath("//*[contains(@text,\"Hello selendroid toast!\")] "));
        System.out.println("toast:" + element.getText());
        System.out.println("toast:" + el2.getText());
        //System.out.println("PageSourc标签：" + driver.getPageSource());
    }

    @AfterAll
    public static void tearDown() {
        //退出driver
        driver.quit();
        System.out.println("退出app");
    }
}
