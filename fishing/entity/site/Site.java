package fishing.entity.site;

import fishing.entity.fisherman.Fisherman;

import java.util.Collection;

public interface Site {

    Collection<Fisherman> getFishermen();

    String getName();

    String getType();

    int getQuota();

    void reduceQuota();

}
