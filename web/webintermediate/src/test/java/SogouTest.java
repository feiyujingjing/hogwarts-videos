import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;

//自动化关键数据记录
@Disabled("禁用测试类")
public class SogouTest {
    public static WebDriver driver;
    //slf4j
    public static Logger logger;

    // 前置
    @BeforeAll
    static void setUpClass() {
        logger = LoggerFactory.getLogger(SogouTest.class);
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        // 打开谷歌浏览器
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
    }

    // 后置
    @AfterAll
    static void tearDownClass() {
        driver.quit();
    }

    //日志记录
    @Test
    @Disabled("禁用sogou")
    public void sogou() {
        // debug 通常记录一些关键步骤信息
        logger.debug("搜狗搜索测试开始");
        driver.get("https://www.sogou.com/");
        driver.findElement(By.id("query")).sendKeys("霍格沃兹测试开发");
        logger.debug("定位方式为ID, 定位元素为query");
        driver.findElement(By.id("stb")).click();
        // info 信息用来打印一些关键结果信息，比如断言
        String res_text = driver.findElement(By.id("sogou_vr_30000000_0")).getText();
        logger.info("查找结果为：" + res_text);
    }

    //截图
    @Test
    @Disabled("禁用sogouScreen")
    public void sogouScreen() throws IOException {
        driver.get("https://www.sogou.com/");
        // 第一种截图方式： 当前页面的截图
        File currentScreen = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        //保存
        FileUtils.copyFile(currentScreen, new File("./image.png"));
        // 截图的第二种方式，查找到某个元素之后进行截图,局部元素解题
        driver.findElement(By.id("query")).sendKeys("霍格沃兹测试开发");
        driver.findElement(By.id("stb")).click();
        // 元素截图
        WebElement resEle = driver.findElement(By.id("sogou_vr_30000000_0"));
        File eleScreen = resEle.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(eleScreen, new File("./image2.png"));
    }

    //打印pageSource
    @Test
    @Disabled("sogouPageSource")
    public void sogouPageSource() throws InterruptedException, IOException {
        driver.get("https://vip.ceshiren.com");
        int i = 0;
        while (i < 3) {
            Thread.sleep(2000);
            String pageSource = driver.getPageSource();
            FileWriter pageSourceFile2 = new FileWriter("./pageSource" + i + ".html");
            pageSourceFile2.write(pageSource);
            i++;
        }

    }
}