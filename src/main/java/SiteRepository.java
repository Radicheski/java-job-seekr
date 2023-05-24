import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SiteRepository implements Serializable, Repository<Site> {

    private static final long serialVersionUID = 1L;

    private List<Site> sites = new ArrayList<>();

    public void add(Site site) {
        sites.add(site);
    }

    public boolean isEmpty() {
        return sites.isEmpty();
    }

    public List<Site> getAll() {
        return List.copyOf(sites);
    }

    public void delete(Site site) {
        List<SiteSnapshot> snapshots = site.getAll();
        snapshots.forEach(site::delete);
        sites.remove(site);
    }

}
