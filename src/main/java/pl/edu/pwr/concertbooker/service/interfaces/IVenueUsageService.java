package pl.edu.pwr.concertbooker.service.interfaces;

import pl.edu.pwr.concertbooker.model.Event;

import java.util.Collection;

public interface IVenueUsageService {
    Collection<Event> getEventsForVenueId(long id);
}
