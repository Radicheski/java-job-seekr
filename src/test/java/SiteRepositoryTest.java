import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
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

    @Test
    void save() {
        Path data = Path.of("data");

        OpenOption[] options = new OpenOption[] {
                StandardOpenOption.CREATE,
                StandardOpenOption.TRUNCATE_EXISTING,
                StandardOpenOption.WRITE
        };

        try (OutputStream os = Files.newOutputStream(data, options);
             ObjectOutputStream oos = new ObjectOutputStream(os)) {
            oos.writeObject(repository);
        } catch (Exception e) {
            e.printStackTrace();
        }

        options = new OpenOption[] {
                StandardOpenOption.READ
        };

        try (InputStream is = Files.newInputStream(data, options);
             ObjectInputStream ois = new ObjectInputStream(is)) {
            SiteRepository repository = (SiteRepository) ois.readObject();
            List<Site> sites = repository.getSites();
            assertEquals(1, sites.size());
            Site site = sites.get(0);
            assertEquals(url, site.getUrl());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
