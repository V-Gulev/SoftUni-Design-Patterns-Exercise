package fishing.entity.site;

import fishing.common.ExceptionMessages;
import fishing.entity.fisherman.Fisherman;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SiteImpl implements Site{
    private String name;
    private String type;
    private int quota;
    private Collection<Fisherman> fishermen;

    public SiteImpl(String name, String type, int quota) {
        setName(name);
        this.type = type;
        this.quota = quota;
        this.fishermen = new ArrayList<>();
    }

    public void setName(String name) {
        if (name.isBlank() || name.isEmpty()) {
            throw new IllegalArgumentException(ExceptionMessages.SITE_NAME_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    @Override
    public Collection<Fisherman> getFishermen() {
        return this.fishermen;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getType() {
        return this.type;
    }

    @Override
    public int getQuota() {
        return this.quota;
    }

    @Override
    public void reduceQuota() {
        quota--;
    }
}
