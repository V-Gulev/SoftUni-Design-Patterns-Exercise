package fishing.entity.fisherman;

import fishing.common.ExceptionMessages;

public class BaseFisherman implements Fisherman {
    private String name;
    private int harvest;
    private int bait;

    public BaseFisherman(String name, int bait) {
        setName(name);
        this.bait = bait;
        this.harvest = 0;
    }

    public void setName(String name) {
        if (name.isEmpty() || name.isBlank()) {
            throw new IllegalArgumentException(ExceptionMessages.FISHERMAN_NAME_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getHarvest() {
        return this.harvest;
    }

    @Override
    public int getBait() {
        return this.bait;
    }

    @Override
    public void fishing() {
        harvest++;
        bait -= 10;
        if (bait < 0) {
            bait = 0;
        }
    }
}
