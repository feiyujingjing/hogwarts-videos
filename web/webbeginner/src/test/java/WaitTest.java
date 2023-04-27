import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static java.lang.Thread.sleep;

/*显示等待与隐式等待*/
public class WaitTest {
    private static WebDriver driver;

    @BeforeAll
    static public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        // 打开谷歌浏览器
        driver = new ChromeDriver(options);
    }

    @AfterAll
    static public void tearDown() {
        // 关闭谷歌浏览器进程
        driver.quit();
    }

    /*浏览器console执行表达式xpath 定位 用$x("")包裹一下*/
    @Test
    void waitSleep() throws InterruptedException {
        driver.get("https://vip.ceshiren.com/");
        sleep(2000);
        driver.findElement(By.xpath("//*[text()='个人中心']")).click();
    }

    //隐式等待,即全局等等
    @Test
    void waitImplicitlyWait() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://vip.ceshiren.com/");
        driver.findElement(By.xpath("//*[text()='个人中心']")).click();

    }

    //显示等待 - 针对某一个元素
    @Test
    void waitUntil() throws InterruptedException {
        driver.get("https://vip.ceshiren.com/#/ui_study/frame");
        WebElement message_btn = new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(By.id("success_btn")));
        message_btn.click();
        sleep(2000);
    }
}
