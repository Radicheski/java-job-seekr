import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class SiteUpdater {

    private Browser browser;

    SiteUpdater(Browser browser) {
        this.browser = browser;
    }

    // TODO Refatorar
    void update(Site site) {
        SiteSnapshot lastSnapshot = site.getLastSnapshot();
        String url = site.getUrl();
        browser.loadUrl(url);
        LocalDateTime accessedDateTime = LocalDateTime.now();
        String text = browser.getText();
        String sourcePage = browser.getSourcePage();
        if (Objects.nonNull(lastSnapshot) &&
                lastSnapshot.getText().equals(text) &&
                lastSnapshot.getPageSource().equals(sourcePage)) {
            return;
        }
        Path screenshot = Path.of(".", UUID.randomUUID() + ".png");
        try {
            Files.move(browser.saveScreenshot(), screenshot);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        SiteSnapshot snapshot = new SiteSnapshot(text, sourcePage, accessedDateTime, screenshot);
        site.add(snapshot);
    }

    void quit() {
        browser.quit();
    }

}
