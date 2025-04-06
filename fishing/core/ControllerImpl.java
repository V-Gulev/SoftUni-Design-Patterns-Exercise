package fishing.core;

import fishing.common.ConstantMessages;
import fishing.common.ExceptionMessages;
import fishing.entity.fisherman.AmateurFisherman;
import fishing.entity.fisherman.Fisherman;
import fishing.entity.fisherman.SkilledFisherman;
import fishing.entity.fishing.Fishing;
import fishing.entity.fishing.FishingImpl;
import fishing.entity.site.Site;
import fishing.entity.site.SiteImpl;
import fishing.repository.Repository;
import fishing.repository.SiteRepository;

import java.util.Collection;
import java.util.List;

public class ControllerImpl implements Controller {

    private final Repository<Site> siteRepository;

    public ControllerImpl() {
        this.siteRepository = new SiteRepository();
    }

    @Override
    public String addSite(String siteType, String siteName, int quota) {
        if (quota < 0) {
            throw new IllegalArgumentException(ExceptionMessages.SITE_QUOTA_CANNOT_BE_NEGATIVE);
        }
        Site site = this.siteRepository.getCollection().stream().filter(site1 -> site1.getName().equals(siteName)).findFirst().orElse(null);
        if (site != null) {
            throw new IllegalArgumentException(ExceptionMessages.EXISTING_SITE);
        }

        site = new SiteImpl(siteName, siteType, quota);
        this.siteRepository.add(site);

        return String.format(ConstantMessages.SITE_ADDED, siteName, siteType);
    }

    @Override
    public String addFisherman(String siteName, String fishermanType, String fishermanName) {
        Site site = this.siteRepository.byName(siteName);
        Fisherman fisherman = site.getFishermen().stream().filter(fisherman1 -> fisherman1.getName().equals(fishermanName)).findFirst().orElse(null);
        if (fisherman != null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.EXISTING_FISHERMAN, fishermanName));
        }

        switch (fishermanType) {
            case "AmateurFisherman" -> fisherman = new AmateurFisherman(fishermanName);
            case "SkilledFisherman" -> fisherman = new SkilledFisherman(fishermanName);
            default -> throw new IllegalArgumentException(ExceptionMessages.INVALID_FISHERMAN);
        }

        site.getFishermen().add(fisherman);

        return String.format(ConstantMessages.FISHERMAN_ADDED, fishermanType, fishermanName);
    }

    @Override
    public String goFishing(String siteName) {
        Site site = this.siteRepository.byName(siteName);
        if (site == null) {
            throw new NullPointerException(String.format(ExceptionMessages.NON_EXISTING_SITE, siteName));
        }

        Fishing fishing = new FishingImpl();
        fishing.startFishing(site);

        if (site.getQuota() > 0) {
            return String.format(ConstantMessages.FISHING_SITE, site.getName(), site.getType(), site.getQuota());
        } else {
            return String.format(ConstantMessages.NO_MORE_FISH_ALLOWED, site.getName(), site.getType());
        }
    }

    @Override
    public String getStatistics() {
        StringBuilder result = new StringBuilder();
        Collection<Site> sites = this.siteRepository.getCollection();

        for (Site site : sites) {
            List<Fisherman> fishermen = site.getFishermen().stream().filter(fisherman -> fisherman.getHarvest() > 0).toList();
            if (fishermen.isEmpty()) {
                continue;
            }
            result.append(String.format("Fishermen fishing on %s %s:", site.getName(), site.getType())).append(System.lineSeparator());
            for (Fisherman fisherman : fishermen) {
                result.append(String.format("Name: %s\nBait left: %d\nCaught fishes: %d\n"
                        , fisherman.getName(), fisherman.getBait(), fisherman.getHarvest()));
            }

        }

        return result.toString();
    }
}
