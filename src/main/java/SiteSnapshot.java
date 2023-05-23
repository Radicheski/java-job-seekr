import java.io.Serializable;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.Objects;

public class SiteSnapshot implements Comparable<SiteSnapshot>, Serializable {

    private static final long serialVersionUID = 1L;

    private String text;
    private String pageSource;
    private LocalDateTime accessedDateTime;
    private String screenshot;

    SiteSnapshot(String text, String pageSource, LocalDateTime accessedDateTime, Path screenshot) {
        this.text = text;
        this.pageSource = pageSource;
        this.accessedDateTime = accessedDateTime;
        this.screenshot = Objects.nonNull(screenshot) ? screenshot.toAbsolutePath().toString() : null;
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
        return Path.of(screenshot);
    }

    @Override
    public int compareTo(SiteSnapshot that) {
        return that.getAccessedDateTime().compareTo(this.getAccessedDateTime());
    }
}
