package com.ceshiren.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

//用户登录
public class LoginPage extends BasePage {

    // 不要暴露页面内部的元素给外部
    //抽取元素
    private By usernameInputBy = By.name("username");
    private By passwordInputBy = By.name("password");
    private By loginButtonBy = By.xpath("//*[text()=\"登录\"]");

    public LoginPage(String baseURL) {
        super(baseURL);
    }

    public LoginPage(WebDriver baseDriver) {
        super(baseDriver);
    }

    //PO 六大原则之一 要用公共方法代表 UI 所提供的功能
    public MainPage login(String username, String password) {

        //输入用户名密码，点击登录
        find(usernameInputBy).clear();
        find(usernameInputBy).sendKeys(username);
        find(passwordInputBy).clear();
        find(passwordInputBy).sendKeys(password);
        find(loginButtonBy).click();
        // PO 六大原则之一方法应该返回其他的 PageObject ，或者返回用于断言的数据
        return new MainPage(driver);

    }

}
