import java.nio.file.Path;
import java.time.LocalDateTime;

public class SiteSnapshot {

    private String text;
    private String pageSource;
    private LocalDateTime accessedDateTime;
    private Path screenshot;

    SiteSnapshot(String text, String pageSource, LocalDateTime accessedDateTime, Path screenshot) {
        this.text = text;
        this.pageSource = pageSource;
        this.accessedDateTime = accessedDateTime;
        this.screenshot = screenshot;
    }

    String getText() {
        return text;
    }

    String getPageSource() {
        return pageSource;
    }

    LocalDateTime getAccessedDateTime() {
        return accessedDateTime;
    }

    Path getScreenshot() {
        return screenshot;
    }
}
