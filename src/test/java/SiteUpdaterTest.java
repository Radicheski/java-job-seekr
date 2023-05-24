import org.junit.jupiter.api.Test;

import java.net.URL;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

public class SiteUpdaterTest {

    private static final String url = "https://www.google.com.br/";

    //TODO Renomear
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

    //TODO Renomear
    @Test
    void test2() {
        Browser browser = new ThrowingBrowser();
        SiteUpdater updater = new SiteUpdater(browser);
        Site site = new Site(url);
        assertThrows(RuntimeException.class, () -> updater.update(site));
        updater.quit();
    }

    @Test
    void createNewSnapshotIfDifferentFromLastOne() {
        Browser browser = new ConstantBrowser();
        SiteUpdater updater = new SiteUpdater(browser);
        Site site = new Site(url);
        updater.update(site);
        SiteSnapshot snapshot = site.getLastSnapshot();
        updater.update(site);
        assertEquals(snapshot, site.getLastSnapshot());
        browser.quit();
    }
}

class ThrowingBrowser extends Browser {
    @Override
    Path saveScreenshot() {
        return null;
    }

}

class ConstantBrowser extends Browser {

    private URL url;

    ConstantBrowser() {
        url = this.getClass().getClassLoader().getResource("ConstantPage.html");
    }

    @Override
    void loadUrl(String url) {
        super.loadUrl(this.url.toString());
    }

}