import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.fail;

//异常自动截图
public class ScreenTest {

    private static WebDriver driver;

    @Test
    public void ceshiren() throws IOException {
        try {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--remote-allow-origins=*");
            driver = new ChromeDriver(options);

            driver.get("https://ceshiren.com/");
            //错误代码，产生报错信息
            driver.findElement(By.id("123"));
        } catch (Exception e) {
            saveScreen();
            driver.quit();
            fail("Exception:" + e);
        }
    }

    private void saveScreen() throws IOException {
        //生成时间戳
        LocalDateTime time = LocalDateTime.now();
        //生成报告，保存文件
        File currentScreen = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(currentScreen, new File("./files/" + time + ".png"));
        Allure.addAttachment("add pic", "image/png", new FileInputStream("./files/" + time + ".png"), ".jpg");

    }
}
