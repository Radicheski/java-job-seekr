import org.junit.jupiter.api.Test;

import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

public class SiteUpdaterTest {

    private static final String url = "https://www.google.com.br/";

    @Test
    void test() {
        Browser browser = new Browser();
        SiteUpdater updater = new SiteUpdater(browser);
        Site site = new Site(url);
        assertTrue(site.isEmpty());
        updater.update(site);
        assertFalse(site.isEmpty());
        updater.quit();
    }

    @Test
    void test2() {
        Browser browser = new ThrowingBrowser();
        SiteUpdater updater = new SiteUpdater(browser);
        Site site = new Site(url);
        assertThrows(RuntimeException.class, () -> updater.update(site));
        updater.quit();
    }
}

class ThrowingBrowser extends Browser {
    @Override
    Path saveScreenshot() {
        return null;
    }

}
