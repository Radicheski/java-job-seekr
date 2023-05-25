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

    SiteSnapshot(String text, String pageSource, Path screenshot) {
        this.text = text;
        this.pageSource = pageSource;
        this.accessedDateTime = LocalDateTime.now();
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SiteSnapshot snapshot = (SiteSnapshot) o;
        return Objects.equals(getText(), snapshot.getText()) && Objects.equals(getPageSource(), snapshot.getPageSource());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getText(), getPageSource());
    }
}
