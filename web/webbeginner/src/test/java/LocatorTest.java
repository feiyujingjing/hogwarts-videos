import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static java.lang.Thread.sleep;

/*常见控件定位*/
public class LocatorTest {
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

    //id定位hogwarts log并点击
    @Test
    void locatorByID() throws InterruptedException {
        driver.get("https://ceshiren.com/");
        WebElement logId = driver.findElement(By.id("site-logo"));
        logId.click();
        // sleep(2000);
    }

    //name定位百度"更多"并点击
    @Test
    void locatorByName() throws InterruptedException {
        driver.get("https://www.baidu.com/");
        driver.findElement(By.name("tj_briicon")).click();
        //sleep(2000);
    }

    //cssSelector定位log
    @Test
    void locatorBycssSelector() throws InterruptedException {
        driver.get("https://ceshiren.com/");
        driver.findElement(By.cssSelector("#site-logo")).click();
        sleep(2000);
    }

    //xpath定位log
    @Test
    void locatorByXpath() {
        driver.get("https://ceshiren.com/");
        driver.findElement(By.xpath("//*[@id=\"site-logo\"]")).click();
    }

    // linktext Idea中Java的UI自动化环境部署
    @Test
    void locatorByLinkText() throws InterruptedException {
        driver.get("https://ceshiren.com/");
        driver.findElement(By.linkText("redis的穿透、击穿、雪崩有什么不同点？")).click();
        //sleep(2000);
    }
}
