package com.ceshiren.page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static java.lang.Thread.sleep;

public class CreateCategoryPage extends BasePage {

    //类目名称输入框
    private By categoryNameInputBy = By.cssSelector(".el-input__inner");
    private By confirmCategoryButtonBy = By.cssSelector(".dialog-footer .el-button--primary");
    private By errorMessageBy = By.cssSelector(".el-form-item__error");

    public CreateCategoryPage(WebDriver baseDriver) {
        super(baseDriver);
    }


    //添加类目,返回类目管理页面
    public CategoryPage createCategoryPage(String categoryName) throws InterruptedException {
        find(categoryNameInputBy).sendKeys(categoryName);
        sleep(2000);
        //显式等待,"确定"可点击后再点击"确定"添加类目
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(confirmCategoryButtonBy))
                .click();

        return new CategoryPage(driver);
    }

    //创建类目失败，提示文案
    public String createCategoryFail() throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        find(categoryNameInputBy).sendKeys("");

        //显式等待,"确定"可点击后再点击"确定"添加类目
        WebElement ele = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(confirmCategoryButtonBy));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click()", ele);

        WebElement errorEle = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(errorMessageBy));
        // sleep(2000);
        logger.debug("错误信息提示：" + errorEle.getText());

        return errorEle.getText();
    }
}
