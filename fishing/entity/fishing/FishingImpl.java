package fishing.entity.fishing;

import fishing.entity.fisherman.Fisherman;
import fishing.entity.site.Site;

import java.util.Collection;
import java.util.List;

public class FishingImpl implements Fishing {

    @Override
    public void startFishing(Site site) {
        List<Fisherman> fishermen = (List<Fisherman>) site.getFishermen();
        if (site.getQuota() <= 0) {
            return;
        }
            for (Fisherman fisherman : fishermen) {
                while (fisherman.getBait() > 0) {
                    fisherman.fishing();
                    site.reduceQuota();
                    if (site.getQuota() <= 0) {
                        return;
                    }
                }
            }

    }
}
