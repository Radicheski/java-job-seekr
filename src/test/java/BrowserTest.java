import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BrowserTest {

    private static Browser browser;
    private static final String url = "https://www.google.com.br/";

    @BeforeAll
    static void setUp() {
        browser = new Browser();
        browser.loadUrl(url);
    }

    @Test
    void checkIfPageIsNotBlank() {
        String text = browser.getText();
        assertFalse(text.isBlank());
    }

    @Test
    void getScreenshot() throws IOException {
        Path file = browser.saveScreenshot();
        assertTrue(Files.exists(file));
        assertTrue(Files.size(file) > 0);
    }

    @Test
    void getSourcePage() {
        String sourcePage = browser.getSourcePage();
        assertFalse(sourcePage.isBlank());
    }

    @AfterAll
    static void quitBrowser() {
        browser.quit();
    }

}
