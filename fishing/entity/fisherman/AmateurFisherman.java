package fishing.entity.fisherman;

public class AmateurFisherman extends BaseFisherman {
    public static final int INITIAL_BAIT = 50;

    public AmateurFisherman(String name) {
        super(name, INITIAL_BAIT);
    }
}
