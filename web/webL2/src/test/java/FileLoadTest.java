import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertAll;

//文件上传的自动化
public class FileLoadTest {
    @Test
    public void file() throws InterruptedException {
        List<Executable> executables = new ArrayList<>();
        WebDriver driver = WebDriverManager.chromedriver().create();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        String url = "https://vip.ceshiren.com/#/ui_study/file_down";
        driver.get(url);
        //业务逻辑处理
        sleep(1000);
        //上传文件
        WebElement inputEle = driver.findElement(By.id("fileInput"));
        inputEle.sendKeys("/Users/dawell/project/hogwartsCode/pom.xml");
        sleep(1000);
        //判断文件是否上传成功
        WebElement textEle = driver.findElement(By.xpath("//*[@id=\"fileInput\"]/../h1"));
        String text = textEle.getText();
        executables.add(() -> assertThat(text, equalTo("pom.xml")));
        assertAll(executables);
    }
}
