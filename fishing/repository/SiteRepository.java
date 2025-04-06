package fishing.repository;

import fishing.entity.site.Site;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SiteRepository implements Repository<Site> {
    private Collection<Site> sites;

    public SiteRepository() {
        this.sites = new ArrayList<>();
    }

    @Override
    public Collection<Site> getCollection() {
        return this.sites;
    }

    @Override
    public void add(Site site) {
        this.sites.add(site);
    }

    @Override
    public boolean remove(Site site) {
        return this.sites.remove(site);
    }

    @Override
    public Site byName(String name) {
        return this.sites.stream().filter(site -> site.getName().equals(name)).findFirst().orElse(null);
    }
}
