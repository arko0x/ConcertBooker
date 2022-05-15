package pl.edu.pwr.concertbooker.service.venueusage;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.edu.pwr.concertbooker.model.Event;
import pl.edu.pwr.concertbooker.model.Venue;
import pl.edu.pwr.concertbooker.repository.EventRepository;
import pl.edu.pwr.concertbooker.repository.VenueRepository;
import pl.edu.pwr.concertbooker.service.interfaces.IVenueUsageService;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class VenueUsageService implements IVenueUsageService {
    private VenueRepository venueRepository;
    private EventRepository eventRepository;

    @Override
    public Collection<Event> getEventsForVenueId(long venueId) {
        Optional<Venue> venueOptional = venueRepository.findById(venueId);
        if (venueOptional.isPresent()) {
            return eventRepository.findByVenue(venueOptional.get());
        }
        return List.of();
    }
}
