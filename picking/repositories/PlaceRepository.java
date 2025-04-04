package picking.repositories;

import picking.entities.places.Place;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class PlaceRepository implements Repository<Place>{
    private Collection<Place> places;

    public PlaceRepository() {
        this.places = new ArrayList<>();
    }

    @Override
    public Collection<Place> getCollection() {
        return Collections.unmodifiableCollection(this.places);
    }

    @Override
    public void add(Place place) {
        this.places.add(place);
    }

    @Override
    public boolean remove(Place place) {
        return this.places.remove(place);
    }

    @Override
    public Place byName(String name) {
        return this.places.stream().filter(place -> place.getName().equals(name)).findFirst().orElse(null);
    }
}
