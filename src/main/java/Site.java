import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;

public class Site implements Iterable<SiteSnapshot> {

    private String url;
    private PriorityQueue<SiteSnapshot> snapshots;

    Site(String url) {
        this.url = url;
        this.snapshots = new PriorityQueue<>(Comparator.comparing(SiteSnapshot::getAccessedDateTime).reversed());
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

}
