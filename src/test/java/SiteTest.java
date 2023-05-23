import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

public class SiteTest {

    private static Site site;
    private static SiteSnapshot snapshotA;
    private static SiteSnapshot snapshotB;
    private static final String url = "https://www.google.com.br/";

    @BeforeAll
    static void setUp() {
        site = new Site(url);
        snapshotA = new SiteSnapshot(null, null,
                LocalDateTime.of(2023, 5, 22, 23, 0, 0), null);
        snapshotB = new SiteSnapshot(null, null,
                LocalDateTime.of(2023, 5, 22, 22, 0, 0), null);
        site.addSnapshot(snapshotA);
        site.addSnapshot(snapshotB);
    }

    @Test
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
    void getIterator() {
        Iterator<SiteSnapshot> iterator = site.iterator();
        assertNotNull(iterator);
    }

}
