import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

/*【实战】测试人论坛搜索功能自动化测试*/
public class CeshirenTest {
    private static WebDriver driver;

    @BeforeAll
    static public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        // 打开谷歌浏览器
        driver = new ChromeDriver(options);
        driver.get("https://ceshiren.com/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterAll
    static public void tearDown() {
        // 关闭谷歌浏览器进程
        driver.quit();
    }

    @Test
    void search() throws InterruptedException {
        //定位并且点击搜索按钮
        driver.findElement(By.id("search-button")).click();
        //定位输入框并且输入关键词
        driver.findElement(By.id("search-term")).sendKeys("selenium");
        //定位到高级搜索按钮， 点击搜索按钮
        driver.findElement(By.cssSelector(".show-advanced-search")).click();
        //定位到第一个搜索结果
        WebElement title = driver.findElement(By.cssSelector(".topic-title"));
        //获取元素文本
        String text = title.getText();
        System.out.println(text);
        //断言
        assertThat(text,containsString("selenium"));
    }

}
