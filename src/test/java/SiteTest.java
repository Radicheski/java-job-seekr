import org.junit.jupiter.api.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

// TODO Tratar caso onde screenshot é null

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SiteTest {

    private static Site site;
    private static SiteSnapshot snapshotA;
    private static SiteSnapshot snapshotB;
    private static final String url = "https://www.google.com.br/";

    @BeforeAll
    static void setUp() throws IOException {
        site = new Site(url);
        Path screenshotA = Path.of("screenshot");
        snapshotA = new SiteSnapshot(null, null, screenshotA);
        Path screenshotB = Files.createTempFile(null, null);
        snapshotB = new SiteSnapshot(null, null, screenshotB);
        site.add(snapshotA);
        site.add(snapshotB);
    }

    @Test
    @Order(1)
    void getLastSnapshot() {
        assertFalse(site.isEmpty());
        SiteSnapshot snapshot = site.getLastSnapshot();
        assertEquals(snapshotA, snapshot);
    }

    @Test
    void checkEmptySite() {
        Site site = new Site(url);
        assertTrue(site.isEmpty());
    }

    @Test
    void getUrl() {
        String url = site.getUrl();
        assertEquals(SiteTest.url, url);
    }

    @Test
    void deleteSnapshot() {
        assertEquals(2, site.snapshotCount());
        SiteSnapshot lastSnapshot = site.getLastSnapshot();
        site.delete(lastSnapshot);
        assertEquals(1, site.snapshotCount());
        lastSnapshot = site.getLastSnapshot();
        site.delete(lastSnapshot);
        assertEquals(0, site.snapshotCount());
    }

    @Test
    void getIterator() {
        Iterator<SiteSnapshot> iterator = site.iterator();
        assertNotNull(iterator);
    }

}
