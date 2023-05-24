import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Site implements Iterable<SiteSnapshot>, Serializable {

    private static final long serialVersionUID = 1L;

    private String url;
    private PriorityQueue<SiteSnapshot> snapshots;

    Site(String url) {
        this.url = url;
        this.snapshots = new PriorityQueue<>();
    }

    boolean isEmpty() {
        return snapshots.isEmpty();
    }

    SiteSnapshot getLastSnapshot() {
        return snapshots.peek();
    }

    String getUrl() {
        return url;
    }

    void addSnapshot(SiteSnapshot snapshot) {
        snapshots.add(snapshot);
    }

    @Override
    public Iterator<SiteSnapshot> iterator() {
        return snapshots.iterator();
    }

    int snapshotCount() {
        return snapshots.size();
    }

    void delete(SiteSnapshot snapshot) {
        Path screenshot = snapshot.getScreenshot();
        try {
            if (Objects.nonNull(screenshot)) Files.delete(screenshot);
        } catch (IOException e) {
            e.printStackTrace();
        }
        snapshots.remove(snapshot);
    }

    List<SiteSnapshot> getSnapshots() {
        return List.copyOf(snapshots);
    }

}
