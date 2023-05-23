import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class SiteRepositoryTest {

    private static SiteRepository repository;
    private static final String url = "https://www.google.com.br/";

    @BeforeAll
    static void setUp() {
        repository = new SiteRepository();
        Site site = new Site(url);
        repository.add(site);
    }

    @Test
    void checkRepositoryIsNotEmpty() {
        assertFalse(repository.isEmpty());
    }

    @Test
    void getUrls() {
        assertEquals(List.of(url), repository.getUrls());
    }

    @Test
    void getSites() {
        List<Site> sites = repository.getSites();
        assertEquals(1, sites.size());
    }

}
