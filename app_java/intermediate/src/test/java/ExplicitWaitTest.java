import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.function.Function;

import static java.lang.Thread.sleep;

public class ExplicitWaitTest {
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
    public void waitWithEleTest() throws InterruptedException {
        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(20), Duration.ofSeconds(2));
        ////只要在dom树中就去点击，不一定肉眼可见
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//*[@text=\"推荐\"]"))).click();
    }

    //推荐 肉眼可见就点击
    @Test
    public void waitWithVisibiEleTest() {
        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(20), Duration.ofSeconds(2));
        FluentWait<WebDriver> webDriverFluentWait = webDriverWait.withMessage("找不到元素");
        webDriverFluentWait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath("//*[@text=\"推荐\"]"))).click();
    }

    //1、推荐 只要在dom树中就点击  2、点击后大盘行情 元素不可见  3、点击推荐第一条进入正文
    @Test
    public void waitWithInVisibiEleTest() throws InterruptedException {
        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(20), Duration.ofSeconds(2));
        //只要在dom树中就去点击，不一定肉眼可见
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//*[@text=\"推荐\"]"))).click();
        //点击后大盘行情 元素不可见
        webDriverWait.until(
                ExpectedConditions.invisibilityOfElementLocated(
                        AppiumBy.xpath("//*[@text=\"大盘行情\"]")
                )
        );
        //点击推荐第一条进入正文
        driver.findElement(AppiumBy.xpath("//*[@resource-id=\"com.xueqiu.android:id/statusText\"]")).click();
        sleep(3000);
    }

    //推荐 元素可点击
    @Test
    public void waitWithEleClickTest() {
        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(20), Duration.ofSeconds(2));
        webDriverWait.until(ExpectedConditions.elementToBeClickable(AppiumBy.xpath("//*[@text=\"推荐\"]"))).click();
    }

    //函数
    @Test
    public void waitWithEleFunTest() {

        Function<WebDriver, Object> webDriverObjectFunction = new Function<>() {
            @Override
            public Object apply(WebDriver webDriver) {
                return webDriver.findElement(AppiumBy.xpath("//*[@text=\"推荐\"]"));
            }
        };
        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(20), Duration.ofSeconds(2));
        //显示等待条件传递
        webDriverWait.until(webDriverObjectFunction);
        driver.findElement(AppiumBy.xpath("//*[@text=\"推荐\"]")).click();
    }

    //lambda
    @Test
    public void waitWithEleLambdaTest() {
        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(20), Duration.ofSeconds(2));
        webDriverWait.until(webDriver -> webDriver.findElement(AppiumBy.xpath("//*[@text=\"推荐\"]")));
        driver.findElement(AppiumBy.xpath("//*[@text=\"推荐\"]")).click();
    }

    @Test
    public void waitWithPageLambdaTest() throws InterruptedException {
        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(20), Duration.ofSeconds(2));
        webDriverWait.until(webDriver ->{
            webDriver.findElement(AppiumBy.xpath("//*[@text=\"推荐\"]")).click();
            return webDriver.getPageSource().contains("com.xueqiu.android:id/statusText");
        });
        driver.findElement(AppiumBy.xpath("//*[@resource-id=\"com.xueqiu.android:id/statusText\"]")).click();
        sleep(1000);
    }

    @AfterAll
    public static void tearDown() {
        //退出driver
        driver.quit();
        System.out.println("退出app");
    }
}
