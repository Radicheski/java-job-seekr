import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Browser {

    private final WebDriver driver = new FirefoxDriver();

    void loadUrl(String url) {
        driver.get(url);
    }

    String getText() {
        return driver.findElement(By.tagName("body")).getText();
    }

    void quit() {
        driver.quit();
    }

}
