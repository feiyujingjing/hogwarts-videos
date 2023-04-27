import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class FireFoxDemo {
    public static void main(String[] args) {
        WebDriver driver = new FirefoxDriver();
        // 打开一个网页
        driver.get("https://ceshiren.com/");
        // 关闭driver进程
        driver.quit();
    }
}

