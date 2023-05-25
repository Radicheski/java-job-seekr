import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.UUID;

public class SiteUpdater {

    private Browser browser;

    SiteUpdater(Browser browser) {
        this.browser = browser;
    }

    void update(Site site) {
        String url = site.getUrl();
        browser.loadUrl(url);

        String text = browser.getText();
        String sourcePage = browser.getSourcePage();

        SiteSnapshot newSnapshot = new SiteSnapshot(text, sourcePage, null);
        SiteSnapshot lastSnapshot = site.getLastSnapshot();
        if (skipSnapshot(lastSnapshot, newSnapshot)) return;

        Path screenshot = Path.of(".", UUID.randomUUID() + ".png");
        try {
            Files.move(browser.saveScreenshot(), screenshot);
            newSnapshot.setScreenshot(screenshot);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        site.add(newSnapshot);
    }

    private boolean skipSnapshot(SiteSnapshot lastSnapshot, SiteSnapshot newSnapshot) {
        return Objects.equals(lastSnapshot, newSnapshot);
    }

    void quit() {
        browser.quit();
    }

}
