import java.io.*;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;

public class Serializer<T extends Serializable> {

    void save(Path file, T object, OpenOption... options) {
        try (OutputStream os = Files.newOutputStream(file, options);
            ObjectOutputStream oos = new ObjectOutputStream(os)) {
            oos.writeObject(object);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    T load(Path file, OpenOption... options) {
        try (InputStream is = Files.newInputStream(file, options);
             ObjectInputStream ois = new ObjectInputStream(is)) {
            return (T) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
