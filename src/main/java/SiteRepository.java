import java.util.ArrayList;
import java.util.List;

public class SiteRepository {

    private List<Site> sites = new ArrayList<>();

    void add(Site site) {
        sites.add(site);
    }

    boolean isEmpty() {
        return sites.isEmpty();
    }

    List<String> getUrls() {
        return sites.stream().map(Site::getUrl).toList();
    }

    List<Site> getSites() {
        return List.copyOf(sites);
    }

}
