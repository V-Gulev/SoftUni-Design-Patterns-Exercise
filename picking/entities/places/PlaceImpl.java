package picking.entities.places;

import picking.common.ExceptionMessages;
import picking.entities.pickers.Picker;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PlaceImpl implements Place{
    private String name;
    private Collection<String> mushrooms;
    private Collection<Picker> pickers;

    public PlaceImpl(String name) {
        setName(name);
        this.mushrooms = new ArrayList<>();
        this.pickers = new ArrayList<>();
    }

    public void setName(String name) {
        if (name.isEmpty() || name.isBlank()) {
            throw new IllegalArgumentException(ExceptionMessages.PICKER_NAME_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Collection<String> getMushrooms() {
        return this.mushrooms;
    }

    @Override
    public Collection<Picker> getPickers() {
        return this.pickers;
    }
}
