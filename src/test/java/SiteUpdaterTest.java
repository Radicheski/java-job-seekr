import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SiteUpdaterTest {

    private static final String url = "https://www.google.com.br/";

    @Test
    void test() {
        SiteUpdater updater = new SiteUpdater();
        Site site = new Site(url);
        assertTrue(site.isEmpty());
        updater.update(site);
        assertFalse(site.isEmpty());
    }
}
