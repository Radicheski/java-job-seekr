import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Site implements Iterable<SiteSnapshot>, Serializable, Repository<SiteSnapshot> {

    private static final long serialVersionUID = 1L;

    private String url;
    private PriorityQueue<SiteSnapshot> snapshots;

    Site(String url) {
        this.url = url;
        this.snapshots = new PriorityQueue<>();
    }

    public boolean isEmpty() {
        return snapshots.isEmpty();
    }

    SiteSnapshot getLastSnapshot() {
        return snapshots.peek();
    }

    String getUrl() {
        return url;
    }

    public void add(SiteSnapshot snapshot) {
        snapshots.add(snapshot);
    }

    @Override
    public Iterator<SiteSnapshot> iterator() {
        return snapshots.iterator();
    }

    int snapshotCount() {
        return snapshots.size();
    }

    public void delete(SiteSnapshot snapshot) {
        Path screenshot = snapshot.getScreenshot();
        try {
            if (Objects.nonNull(screenshot)) Files.delete(screenshot);
        } catch (IOException e) {
            e.printStackTrace();
        }
        snapshots.remove(snapshot);
    }

    public List<SiteSnapshot> getAll() {
        return List.copyOf(snapshots);
    }

}
