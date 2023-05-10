import com.ceshiren.page.CategoryPage;
import com.ceshiren.page.LoginPage;
import com.ceshiren.page.MainPage;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.WebElement;

import java.util.List;

import static java.lang.Thread.sleep;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CategoryPageTest {
    public static LoginPage loginPage;
    public static MainPage mainPage;

    //所有用例执行之前登录
    @BeforeAll
    public static void setupClass() {
        loginPage = new LoginPage("https://litemall.hogwarts.ceshiren.com/#/login");
        mainPage = loginPage.login("hogwarts", "test12345");
    }

    //所有用例执行后关闭浏览器
    @AfterAll
    static void tearDownClass() {
        loginPage.quitDriver();
    }

    //使用url 跳转的方式，回退到用例执行之前的初始状态，保证下个用例仍然能够正常的执行
    @AfterEach
    void tearDown() {
        mainPage.getURL("https://litemall.hogwarts.ceshiren.com/#/dashboard");
    }

    //添加和删除商品类目
    @ParameterizedTest
    @ValueSource(strings = {"测试商品hogwarts1", "测试商品hogwarts2"})
    public void CategoryManageTest(String categoryName) throws InterruptedException {
        CategoryPage categoryPage = mainPage
                .gotoCategoryPage()
                .gotoCreateCategoryPage()
                .createCategoryPage(categoryName);
        //在商品类目列表获取结果
        List<WebElement> res = categoryPage.getResult(categoryName);
        //添加断言
        assertEquals(1, res.size());
        //在商品列表页面删除对应的商品信息
        categoryPage.deleteCategory(categoryName);
    }

    //获取错误提示文案
    @Test
    public void CategoryManageFail() throws InterruptedException {
        String res = mainPage
                .gotoCategoryPage()
                .gotoCreateCategoryPage()
                .createCategoryFail();
        assertEquals("类目名不能为空", res);
    }
}
