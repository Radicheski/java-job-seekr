import org.junit.jupiter.api.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SerializerTest {

    private static Path file;
    private static Path nonExisitingFile = Path.of("data/null");
    private static Serializer<String> serializer = new Serializer<>();
    private static String testString = "StringObject";

    @BeforeAll
    static void setUp() throws IOException {
        file = Files.createTempFile("test", null);
    }

    @Test
    @Order(1)
    void save() throws IOException {
        assertEquals(0, Files.size(file));
        serializer.save(file, testString);
        assertNotEquals(0, Files.size(file));
    }

    @Test
    @Order(2)
    void load() {
        String string = serializer.load(file);
        assertEquals(testString, string);
    }

    @Test
    void failedSave() {
        assertDoesNotThrow(() -> serializer.save(nonExisitingFile, testString));
    }

    @Test
    void failedLoad() {
        assertDoesNotThrow(() -> serializer.load(nonExisitingFile));
    }

    @AfterAll
    static void tearDown() throws IOException{
        Files.deleteIfExists(file);
    }

}
