import java.io.Serializable;
import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;

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

}
