import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class TestPage {

    private static Browser browser;

    @Test
    void checkIfPageIsNotBlank() {
        final String url = "https://www.google.com.br/";
        browser = new Browser();
        browser.loadUrl(url);
        String text = browser.getText();
        assertFalse(text.isBlank());
    }

    @AfterAll
    static void quitBrowser() {
        browser.quit();
    }

}
