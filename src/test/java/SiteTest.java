import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SiteTest {

    @Test
    void getSite() {
        Site site = new Site();
        assertFalse(site.isEmpty());
        SiteSnapshot snapshot = site.getLastSnapshot();
        assertNotNull(snapshot);
    }
}
