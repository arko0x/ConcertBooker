package pl.edu.pwr.concertbooker.service.interfaces;

import pl.edu.pwr.concertbooker.exception.custom.CannotEditEventWithAlreadySoldTicketsException;
import pl.edu.pwr.concertbooker.exception.custom.EntityNotFoundException;
import pl.edu.pwr.concertbooker.exception.custom.NotUserTicketException;
import pl.edu.pwr.concertbooker.model.Event;
import pl.edu.pwr.concertbooker.service.event.dto.CreateEventDto;
import pl.edu.pwr.concertbooker.service.event.dto.EventInfoDto;
import pl.edu.pwr.concertbooker.service.event.dto.EventInfoWithVenueDto;
import pl.edu.pwr.concertbooker.service.event.dto.UpdateEventDto;

import java.util.Collection;

public interface IEventService {
    void createEventWithTickets(CreateEventDto eventDto) throws EntityNotFoundException;
    void updateEvent(UpdateEventDto eventDto) throws EntityNotFoundException, CannotEditEventWithAlreadySoldTicketsException;
    Collection<EventInfoDto> getAllEvents();
    EventInfoDto getEventById(long id) throws EntityNotFoundException;
    Collection<Event> getEventsForVenueId(long venueId);
    Event getEvent(long id) throws EntityNotFoundException;
    EventInfoWithVenueDto getEventWithVenue(long id) throws EntityNotFoundException;
    void cancelEventById(long id) throws EntityNotFoundException, NotUserTicketException;
}
