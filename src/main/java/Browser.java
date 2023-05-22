import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;

public class Browser {

    private final FirefoxDriver driver = new FirefoxDriver();
    private final static By BODY = By.tagName("body");

    void loadUrl(String url) {
        driver.get(url);
    }

    String getText() {
        return driver.findElement(BODY).getText();
    }

    void quit() {
        driver.quit();
    }

    File saveScreenShot() {
        return driver.getFullPageScreenshotAs(OutputType.FILE);
    }

}
