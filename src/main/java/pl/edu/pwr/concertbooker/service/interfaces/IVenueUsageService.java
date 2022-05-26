package pl.edu.pwr.concertbooker.service.interfaces;

import pl.edu.pwr.concertbooker.exception.custom.EntityNotFoundException;
import pl.edu.pwr.concertbooker.model.Event;
import pl.edu.pwr.concertbooker.model.Venue;
import pl.edu.pwr.concertbooker.service.venue.dto.VenueInfoDto;

import java.util.Collection;

public interface IVenueUsageService {
    Collection<Event> getEventsForVenueId(long id);
    VenueInfoDto getVenue(long id) throws EntityNotFoundException;
}
