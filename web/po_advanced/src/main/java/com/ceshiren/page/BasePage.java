package com.ceshiren.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.List;

public class BasePage {
    public WebDriver driver;
    public String baseURL;
    public Logger logger = LoggerFactory.getLogger(BasePage.class);

    //打开网页
    public BasePage(String baseURL) {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        //隐式等待
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get(baseURL);
        //窗口最大化
        driver.manage().window().maximize();

    }

    public BasePage(WebDriver baseDriver) {
        driver = baseDriver;
    }

    // 问题 大量的driver find ：
    //  1. 每次查找元素添加日志
    // 解决方案： 不要对外暴漏driver，直接封装在basepage
    public WebElement find(By by) {
        logger.debug("查找的元素为：" + by);
        return driver.findElement(by);
    }

    public List<WebElement> finds(By by) {
        return driver.findElements(by);
    }

    public void getURL(String url) {
        driver.get(url);
    }

    //关掉页面
    public void quitDriver() {
        driver.quit();
    }

}
