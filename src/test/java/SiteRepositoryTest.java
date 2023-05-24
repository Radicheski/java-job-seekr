import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
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
    @Order(1)
    void checkRepositoryIsNotEmpty() {
        assertFalse(repository.isEmpty());
    }

    @Test
    @Order(1)
    void getUrls() {
        assertEquals(List.of(url), repository.getUrls());
    }

    @Test
    @Order(1)
    void getSites() {
        List<Site> sites = repository.getSites();
        assertEquals(1, sites.size());
    }

    @Test
    void deleteSite() {
        Site site = repository.getSites().get(0);
        assertFalse(repository.isEmpty());
        repository.delete(site);
        assertTrue(repository.isEmpty());
    }

}
