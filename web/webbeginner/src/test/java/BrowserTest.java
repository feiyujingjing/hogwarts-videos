import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

/*web浏览器控制*/
public class BrowserTest {
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

    // 测试用例-打开测试人网页
    @Test
    public void windowGet() {
        driver.get("https://ceshiren.com/");
    }

    // 测试用例-刷新页面
    @Test
    public void windowRefresh() {
        driver.get("https://ceshiren.com/");
        driver.navigate().refresh();
    }

    //测试用例-浏览器的回退
    @Test
    public void windowBack() {
        driver.get("https://ceshiren.com/");
        driver.get("https://www.baidu.com/");
        driver.navigate().back();
    }

    //测试用例-浏览器的前进操作
    @Test
    public void windowForward() {
        driver.get("https://ceshiren.com/");
        driver.get("https://www.baidu.com/");
        driver.navigate().back();
        driver.navigate().forward();
    }
// 测试用例-浏览器最大化
    @Test
    public void windowMax() throws InterruptedException {
        driver.get("https://ceshiren.com/");
        driver.manage().window().maximize();
        Thread.sleep(2000);
    }
    // 测试用例-浏览器最小化
    @Test
    public void windowMin() throws InterruptedException {
        driver.get("https://ceshiren.com/");
        driver.manage().window().minimize();
        Thread.sleep(2000);
    }


}
