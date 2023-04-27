import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static java.lang.Thread.sleep;
import static org.junit.jupiter.api.Assertions.assertEquals;

/*
自动化测试用例结构分析*/
public class TestSogouTest {
    private static WebDriver driver;

    @BeforeAll
    static public void setUp() {
        // 打开谷歌浏览器
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

    @Test
    public void testSogou() throws InterruptedException {
        driver.get("https://www.sogou.com/");
        driver.findElement(By.id("query")).sendKeys("霍格沃兹测试开发");
        //键盘点击回车键
        driver.findElement(By.id("query")).sendKeys(Keys.ENTER);
        sleep(2000);
        String text = driver.findElement(By.cssSelector("#sogou_vr_30000000_0 > em")).getText();
        assertEquals("霍格沃兹测试开发", text);
    }
}
