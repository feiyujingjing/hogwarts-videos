package com.ceshiren.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage extends BasePage {
    // 问题： 虽然继承了，但是依然一直重复的打开浏览器， 每次new 一个新的page 只要继承了 base page 就会不停的重复打开浏览器
    // 解决：
    private By manageMarketButton = By.xpath("//*[text()=\"商场管理\"]");
    private By categoryButton = By.xpath("//*[text()=\"商品类目\"]");

    public MainPage(WebDriver baseDriver) {
        super(baseDriver);
    }

    //PO 六大原则之一 要用公共方法代表 UI 所提供的功能
    public CategoryPage gotoCategoryPage() {
        find(manageMarketButton).click();
        find(categoryButton).click();
        return new CategoryPage(driver);
    }
}
