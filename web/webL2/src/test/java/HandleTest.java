import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertAll;

//网页frame与多窗口处理
public class HandleTest {

    @Test
    public void twoHandleSwitch() {
        List<Executable> executable = new ArrayList<>();
        //1.声明web driver
        WebDriver webDriver = WebDriverManager.chromedriver().create();
        //2.声明隐式等待
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        //3.打开要操作的网页
        String url = "https://vip.ceshiren.com/#/ui_study/frame";
        webDriver.get(url);
        //4.获取打开页面的句柄
        String originalWindow = webDriver.getWindowHandle();
        System.out.println("第一个页面的句柄" + originalWindow);
        // 打开了一个新的窗口
        WebElement element = webDriver.findElement(By.xpath("//*[text()='元素定位']"));
        element.click();
        //获取所有窗口的句柄
        Set<String> allHandles = webDriver.getWindowHandles();
        //当前页面标题
        String title = webDriver.getTitle();
        System.out.println("当前页面标题：" + title);

        //切换窗口
        for (String windowHandle : allHandles) {
            if (!windowHandle.equals(originalWindow)) {
                webDriver.switchTo().window(windowHandle);
                break;
            }
        }
        //新窗口
        String title2 = webDriver.getTitle();
        System.out.println("当前页面标题：" + title2);
        executable.add(() -> assertThat(title2, containsString("测试人社区")));

        //切换到默认页面
        webDriver.switchTo().window(originalWindow);
        String title3 = webDriver.getTitle();
        System.out.println("当前页面标题：" + title3);
        executable.add(() -> assertThat(title3, containsString("霍格沃兹测试开发")));
        assertAll(executable);
    }

    //切换frame
    @Test
    public void frame() {
        List<Executable> executables = new ArrayList<>();
        //1.声明webdriver
        WebDriver driver = WebDriverManager.chromedriver().create();
        //2.隐式等待声明
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        //3.打开要操作的网页
        String url = "https://vip.ceshiren.com/#/ui_study/frame";
        driver.get(url);
        //4.业务逻辑操作
        driver.switchTo().frame("frame1");
        WebElement btn1 = driver.findElement(By.xpath("//*[text()=\"练习按钮ui_frame1\"]"));
        String text1 = btn1.getText();
        System.out.println("第一个按钮：" + text1);
        executables.add(() -> assertThat(text1, equalTo("练习按钮ui_frame1")));

        //切换到父框架
        driver.switchTo().parentFrame();
        //切换到第二个frame
        driver.switchTo().frame("Main");
        WebElement btn2 = driver.findElement(By.xpath("//*[text()=\"练习按钮ui_frame3\"]"));
        String text2 = btn2.getText();
        System.out.println("第一个按钮：" + text2);
        executables.add(() -> assertThat(text2, equalTo("练习按钮ui_frame3")));

        assertAll(executables);
    }

}

