import java.io.File;
import java.time.LocalDateTime;

public class SiteUpdater {

    void update(Site site) {
        Browser browser = new Browser();
        String url = site.getUrl();
        browser.loadUrl(url);
        LocalDateTime accessedDateTime = LocalDateTime.now();
        String text = browser.getText();
        String sourcePage = browser.getSourcePage();
        File screenshot = browser.saveScreenshot();
        SiteSnapshot snapshot = new SiteSnapshot(text, sourcePage, accessedDateTime, screenshot);
        site.addSnapshot(snapshot);
        browser.quit();
    }

}
