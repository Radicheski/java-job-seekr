import net.bytebuddy.matcher.IsNamedMatcher;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class SiteSnapshotTest {

    private static SiteSnapshot snapshot;

    @BeforeAll
    static void setUp() throws IOException {
        String text = "Some text";
        String pageSource = "<html><body>SomeCode</body></html>";
        LocalDateTime accessedDateTime = LocalDateTime.now();
        File screenshot = File.createTempFile("screenshot", null);

        snapshot = new SiteSnapshot(text, pageSource , accessedDateTime, screenshot);
    }

    @Test
    void getText() {
        String text = snapshot.getText();
        assertFalse(text.isBlank());
    }

    @Test
    void getPageSource() {
        String pageSource = snapshot.getPageSource();
        assertFalse(pageSource.isBlank());
    }

    @Test
    void getAccessedDate() {
        LocalDateTime dateTime = snapshot.getAccessedDateTime();
        assertNotNull(dateTime);
    }

    @Test
    void getScreenshot() {
        Object screenshot = snapshot.getScreenshot();
        assertNotNull(screenshot);
    }

    @AfterAll
    static void tearDown() {
        File screenshot = snapshot.getScreenshot();
        screenshot.delete();
    }

}
