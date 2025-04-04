package picking.entities.action;

import picking.entities.pickers.Picker;
import picking.entities.places.Place;

import java.util.Collection;
import java.util.List;

public class ActionImpl implements Action {

    @Override
    public void startPicking(Place place) {
        Collection<String> mushrooms = place.getMushrooms();
        List<Picker> pickers = (List<Picker>) place.getPickers();

        for (Picker picker : pickers) {
            while (picker.getVitality() > 0 && mushrooms.iterator().hasNext()) {
                String mushroom = mushrooms.iterator().next();
                picker.pick();

                if (mushroom.startsWith("poisonous")) {
                    picker.getBag().getMushrooms().clear();
                } else {
                    picker.getBag().getMushrooms().add(mushroom);
                }

                mushrooms.remove(mushroom);

            }

        }

    }
}
