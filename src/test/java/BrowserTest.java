import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BrowserTest {

    private static Browser browser;
    private final String url = "https://www.google.com.br/";

    @Test
    void checkIfPageIsNotBlank() {
        browser = new Browser();
        browser.loadUrl(url);
        String text = browser.getText();
        assertFalse(text.isBlank());
    }

    @Test
    void getScreenshot() {
        browser = new Browser();
        browser.loadUrl(url);
        File file = browser.saveScreenShot();
        assertTrue(file.exists());
        assertTrue(file.length() > 0);
    }

    @AfterEach
    void quitBrowser() {
        browser.quit();
    }

}
