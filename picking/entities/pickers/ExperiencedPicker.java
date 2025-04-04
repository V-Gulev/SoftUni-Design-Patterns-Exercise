package picking.entities.pickers;

public class ExperiencedPicker extends BasePicker{
    public static final int INITIAL_UNITS_OF_VITALITY = 60;

    public ExperiencedPicker(String name) {
        super(name, INITIAL_UNITS_OF_VITALITY);
    }
}
