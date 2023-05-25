import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class SiteSnapshotTest {

    private static SiteSnapshot snapshot;

    @BeforeAll
    static void setUp() throws IOException {
        String text = "Some text";
        String pageSource = "<html><body>SomeCode</body></html>";
        Path screenshot = Files.createTempFile("screenshot", null);

        snapshot = new SiteSnapshot(text, pageSource , screenshot);
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

    @Test
    void testEquals() {
        SiteSnapshot snapshotA = new SiteSnapshot("Text", "<html></html>", null);
        SiteSnapshot snapshotB = new SiteSnapshot("Text", "<html></html>", null);
        assertNotSame(snapshotA, snapshotB);
        assertEquals(snapshotA, snapshotB);
        assertEquals(snapshotA.hashCode(), snapshotB.hashCode());
    }

    @AfterAll
    static void tearDown() throws IOException {
        Path screenshot = snapshot.getScreenshot();
        Files.deleteIfExists(screenshot);
    }

}
