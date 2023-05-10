import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.junit.jupiter.api.*;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

//Cookie复用
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CookieTest {
    private static WebDriver driver;
    private static WebDriverWait wait;
    ObjectMapper mapper = new ObjectMapper(new YAMLFactory());

    @BeforeAll
    public static void setUpClass() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    //获取cookie并写入文件
    @Test
    @Order(1)
    public void saveCookies() throws IOException {
        driver.get("https://work.weixin.qq.com/wework_admin/loginpage_wx");
        //30s内手动扫码登录，确认登录成功后获取cookie
        wait.until(ExpectedConditions.urlContains("https://work.weixin.qq.com/wework_admin/frame"));
        //获取cookie
        Set<Cookie> cookies = driver.manage().getCookies();
        // 将cookie 写入文件当中(yaml，写入文件操作)
        mapper.writeValue(new File("cookies.yaml"), cookies);

    }

    //读取cookie信息，加载到浏览器中
    @Test
    @Order(2)
    public void loadCookies() throws IOException {
        driver.get("https://work.weixin.qq.com/wework_admin/loginpage_wx");
        //读取cookie信息
        TypeReference<List<HashMap<String, Object>>> typeReference = new TypeReference<>() {
        };
        List<HashMap<String, Object>> cookies = mapper.readValue(new File("cookies.yaml"), typeReference);
        cookies.stream().forEach(
                cookie -> {
                    driver.manage().addCookie(
                            new Cookie(cookie.get("name").toString(), cookie.get("value").toString())
                    );
                }
        );

        //刷新页面
        driver.navigate().refresh();
    }
}
