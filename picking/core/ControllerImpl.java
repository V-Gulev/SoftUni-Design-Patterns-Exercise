package picking.core;

import picking.common.ConstantMessages;
import picking.common.ExceptionMessages;
import picking.entities.action.Action;
import picking.entities.action.ActionImpl;
import picking.entities.pickers.ExperiencedPicker;
import picking.entities.pickers.JuniorPicker;
import picking.entities.pickers.Picker;
import picking.entities.places.Place;
import picking.entities.places.PlaceImpl;
import picking.repositories.PlaceRepository;
import picking.repositories.Repository;

import java.util.Collection;
import java.util.List;

public class ControllerImpl implements Controller {

    private final Repository<Place> placeRepository;

    public ControllerImpl() {
        this.placeRepository = new PlaceRepository();
    }

    @Override
    public String addPlace(String placeName, String... mushrooms) {
        Place place = new PlaceImpl(placeName);
        place.getMushrooms().addAll(List.of(mushrooms));
        placeRepository.add(place);
        return String.format(ConstantMessages.PLACE_ADDED, placeName);
    }

    @Override
    public String addPicker(String placeName, String pickerType, String pickerName) {
        Place place = placeRepository.byName(placeName);
        Picker picker = place.getPickers().stream().filter(p -> p.getName().equals(pickerName)).findFirst().orElse(null);
        if (picker != null) {
            throw new IllegalArgumentException(ExceptionMessages.EXISTING_PICKER);
        }

        switch (pickerType) {
            case "JuniorPicker" -> picker = new JuniorPicker(pickerName);
            case "ExperiencedPicker" -> picker = new ExperiencedPicker(pickerName);
            default -> throw new IllegalArgumentException(ExceptionMessages.INVALID_PICKER);
        }

        place.getPickers().add(picker);
        return String.format(ConstantMessages.PICKER_ADDED, pickerType, pickerName);
    }

    @Override
    public String startPicking(String placeName) {
        Place place = placeRepository.byName(placeName);
        if (place == null) {
            throw new NullPointerException(String.format(ExceptionMessages.NON_EXISTING_PLACE, placeName));
        }
        Action action = new ActionImpl();
        action.startPicking(place);

        return String.format(ConstantMessages.PLACE_VISITED, placeName);
    }

    @Override
    public String getStatistics() {
        StringBuilder builder = new StringBuilder();
        Collection<Place> places = placeRepository.getCollection();
        for (Place place : places) {
            builder.append(String.format("Pickers in the %s:", place.getName())).append(System.lineSeparator());
            Collection<Picker> pickers = place.getPickers();

            for (Picker picker : pickers) {
                String mushrooms;
                if (picker.getBag().getMushrooms().isEmpty()) {
                    mushrooms = "none";
                } else {
                    mushrooms = String.join(", ", picker.getBag().getMushrooms());
                }
                builder.append(String.format("Name: %s\nVitality: %d\nBag mushrooms: %s\n"
                        , picker.getName(), picker.getVitality(), mushrooms));
            }
        }
        return builder.toString();
    }
}
