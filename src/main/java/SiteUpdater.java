import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.UUID;

public class SiteUpdater {

    private Browser browser;

    SiteUpdater(Browser browser) {
        this.browser = browser;
    }

    void update(Site site) {
        String url = site.getUrl();
        browser.loadUrl(url);
        LocalDateTime accessedDateTime = LocalDateTime.now();
        String text = browser.getText();
        String sourcePage = browser.getSourcePage();
        Path screenshot = Path.of(".", UUID.randomUUID() + ".png");
        try {
            Files.move(browser.saveScreenshot(), screenshot);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        SiteSnapshot snapshot = new SiteSnapshot(text, sourcePage, accessedDateTime, screenshot);
        site.addSnapshot(snapshot);
    }

    void quit() {
        browser.quit();
    }

}
