package com.ceshiren.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CategoryPage extends BasePage {
    private By addButtonBy = By.cssSelector(".el-icon-edit");

    public CategoryPage(WebDriver baseDriver) {
        super(baseDriver);
    }

    //点击"添加"，跳转到添加类目页面
    public CreateCategoryPage gotoCreateCategoryPage() {
        find(addButtonBy).click();
        return new CreateCategoryPage(driver);
    }

    //删除某个类目
    public void deleteCategory(String categoryName) {
        driver.findElement(By.xpath("//*[text()=\"" + categoryName + "\"]/../..//*[text()=\"删除\"]")).click();
    }

    //根据类目名称获取类目
    public List<WebElement> getResult(String categoryName) {
        List<WebElement> eles = finds(By.xpath("//*[text()='" + categoryName + "']"));
        return eles;
    }


}
