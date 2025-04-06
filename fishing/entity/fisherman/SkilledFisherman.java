package fishing.entity.fisherman;

public class SkilledFisherman extends BaseFisherman{
    public static final int INITIAL_BAIT = 100;

    public SkilledFisherman(String name) {
        super(name, INITIAL_BAIT);
    }
}
