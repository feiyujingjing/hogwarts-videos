import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static java.lang.Thread.sleep;

/*常见控件交互*/
public class InteracitonTest {
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

    //用例-输入 清空 点击
    @Test
    public void inputClickClear() throws InterruptedException {
        // 打开Sogou页面
        driver.get("https://www.sogou.com/");
        WebElement query = driver.findElement(By.id("query"));
        query.sendKeys("霍格沃兹测试开发");
        sleep(1000);
        query.clear();
        sleep(1000);
        //点击搜索按钮
        WebElement search = driver.findElement(By.id("stb"));
        search.click();
        sleep(1000);
    }

    //获取属性值
    @Test
    public void getAttrbute() {
        driver.get("https://vip.ceshiren.com/#/ui_study/frame");
        WebElement locate_ele = driver.findElement(By.id("locate_id"));
        //获取元素文本信息
        System.out.println(locate_ele.getText());
        //获取元素对应属性值
        System.out.println(locate_ele.getAttribute("style"));

    }
}
