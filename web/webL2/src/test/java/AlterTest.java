
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;

import static java.lang.Thread.sleep;
import static org.junit.jupiter.api.Assertions.assertTrue;

//弹框处理
public class AlterTest {
    //alter弹窗
    public void alter() throws InterruptedException {
        WebDriver driver = WebDriverManager.chromedriver().create();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        String url = "https://vip.ceshiren.com/#/ui_study/frame";
        driver.get(url);
        //业务逻辑处理
        WebElement alterEle = driver.findElement(By.xpath("//*[text()=\"alert弹框\"]"));
        alterEle.click();
        sleep(1000);
        //切换到弹窗
        Alert alert = driver.switchTo().alert();
        //获取文本
        String text = alert.getText();
        System.out.println("弹窗文本" + text);
        //点击确认按钮
        alert.accept();

    }

    //文本弹窗
    @Test
    public void tanchuang() throws InterruptedException {
        WebDriver driver = WebDriverManager.chromedriver().create();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        String url = "https://vip.ceshiren.com/#/ui_study/frame";
        driver.get(url);
        //业务逻辑
        WebElement tanchuangEle = driver.findElement(By.id("primary_btn"));
        tanchuangEle.click();
        tanchuangEle.click();
        sleep(2000);
        //点击确定
        WebElement confirm = driver.findElement(By.xpath("//*[contains(text(),\"确定\")]"));
        confirm.click();
        sleep(2000);
        String pageSource = driver.getPageSource();
        assertTrue(pageSource.contains("该弹框点击两次后才会弹出"));
    }

}
