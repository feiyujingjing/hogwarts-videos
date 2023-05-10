import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static java.lang.Thread.sleep;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertAll;

//只要是键盘鼠标相关的操作就要声明 Actions 对象
//键盘 相关的操作，在操作完成后需要 .build().perform(); 才能真正执行
//鼠标 相关操作，在操作完成后需要 .perform(); 才能真正执行成功

public class ActionTest {

    //键盘回车键
    @Test
    public void enter() {
        List<Executable> executableList = new ArrayList<>();
        //1.声明web driver
        WebDriver webDriver = WebDriverManager.chromedriver().create();
        //2.声明隐式等待
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        //3.打开要操作的网页
        String url = "https://vip.ceshiren.com/#/ui_study/keypress";
        webDriver.get(url);
        WebElement textEle = webDriver.findElement(By.className("mb-2"));
        //键盘操作:
        String beforeText = textEle.getText();
        System.out.println("键盘操作之前的文本内容：" + beforeText);
        executableList.add(() -> assertThat(beforeText, equalTo("键盘操作:")));

        //4.进行业务逻辑操作
        //4.1 定位输入框
        WebElement sendEle = webDriver.findElement(By.className("el-input__inner"));
        //4.2 输入文本并在键盘上点击回车按键
        //开始鼠标、键盘的操作
        Actions actions = new Actions(webDriver);
        actions.sendKeys(sendEle, "selenium")
                .sendKeys(Keys.ENTER)
                .build()
                .perform();

        //4.3 获取页面显示文本内容 断言
        String afterText = textEle.getText();
        System.out.println("键盘操作之前的文本内容：" + afterText);
        executableList.add(() -> assertThat(afterText, equalTo("键盘操作: selenium")));
        assertAll(executableList);
    }

    //多窗口操作
    @Test
    public void commandWithClick() throws InterruptedException {
        //1.声明web driver
        WebDriver webDriver = WebDriverManager.chromedriver().create();
        //2.声明隐式等待
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        //3.打开要操作的网页
        String url = "https://ceshiren.com/";
        webDriver.get(url);
        //4.1 找到要点击的元素 及其文本
        WebElement firstEle = webDriver.findElement(By.xpath("//*[@role=\"heading\"]"));
        String text = firstEle.getText();
        System.out.println("第一个窗口帖子文本" + text);
        //获取当前窗口
        String originalWindow = webDriver.getWindowHandle();
        //4.2 键盘command+元素点击同时进行
        Actions actions = new Actions(webDriver);
        actions.keyDown(Keys.COMMAND)
                .click(firstEle)
                .keyUp(Keys.COMMAND)
                .build()
                .perform();
        //4.3 窗口切换，判断新窗口的标题 断言
        Set<String> windowHandles = webDriver.getWindowHandles();
        for (String windowHandle : windowHandles) {
            if (!windowHandle.equals(originalWindow)) {
                webDriver.switchTo().window(windowHandle);
                break;
            }
        }
        String title = webDriver.getTitle();
        System.out.println("新窗口的标题是：" + title);
        sleep(2000);
        assertThat(title, containsString(text));
    }

    //鼠标点击操作
    @Test
    public void mouseClick() throws InterruptedException {
        List<Executable> executable = new ArrayList<>();
        //1.声明web driver
        WebDriver webDriver = WebDriverManager.chromedriver().create();
        //2.声明隐式等待
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        //3.打开要操作的网页
        String url = "https://dev.studentfrontend.ceba.ceshiren.com/#/ui_study/clicks";
        webDriver.get(url);
        //4.业务逻辑执行
        WebElement textEle = webDriver.findElement(By.className("mb-2"));
        String beforeText = textEle.getText();
        System.out.println("点击之前的文本内容：" + beforeText);
        executable.add(() -> assertThat(beforeText, equalTo("点击按钮:")));
        sleep(1000);
        Actions actions = new Actions(webDriver);
        //鼠标单击
        WebElement clickEle = webDriver.findElement(By.xpath("//*[text()='单击按钮']"));
        actions.click(clickEle).perform();
        String clickText = textEle.getText();
        System.out.println("单击之后的文本内容：" + clickText);
        executable.add(() -> assertThat(clickText, equalTo("点击按钮: 我是单击按钮--")));
        sleep(1000);

        //鼠标双击
        WebElement dbclickEle = webDriver.findElement(By.id("dblclick"));
        actions.doubleClick(dbclickEle).perform();
        String doubbleText = textEle.getText();
        System.out.println("双击之后的文本内容：" + doubbleText);
        executable.add(() -> assertThat(doubbleText, containsString("双击按钮")));

        //鼠标的右键按钮
        WebElement rightClickEle = webDriver.findElement(By.id("rightClick"));
        actions.contextClick(rightClickEle).perform();
        String rightClickText = textEle.getText();
        System.out.println("右击之后的文本内容：" + rightClickText);
        executable.add(() -> assertThat(rightClickText, containsString("鼠标右键")));
        sleep(1000);

        assertAll(executable);
    }

    //鼠标悬浮
    @Test
    public void mouseover() throws InterruptedException {
        List<Executable> executable = new ArrayList<>();
        //1.声明webdriver
        WebDriver webDriver = WebDriverManager.chromedriver().create();
        //2.声明隐式等待
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        //3.打开要操作的网页
        String url = "https://dev.studentfrontend.ceba.ceshiren.com/#/ui_study/mouseover";
        webDriver.get(url);
        //业务逻辑处理
        WebElement element = webDriver.findElement(By.className("mb-2"));
        String text = element.getText();
        System.out.println("鼠标悬停前的文本内容：" + text);
        executable.add(() -> assertThat(text, equalTo("鼠标操作: ")));
        sleep(1000);
        //鼠标移动到悬停按钮
        WebElement mouseEle = webDriver.findElement(By.xpath("//*[contains(text(),'鼠标移入')]"));
        Actions actions = new Actions(webDriver);
        actions.moveToElement(mouseEle).perform();
        String mouseText = element.getText();
        System.out.println("鼠标悬停时的文本内容：" + mouseText);
        executable.add(() -> assertThat(mouseText, equalTo("鼠标操作: 鼠标移入")));

    }
}
