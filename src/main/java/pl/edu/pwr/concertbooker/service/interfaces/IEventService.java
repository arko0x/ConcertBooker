package pl.edu.pwr.concertbooker.service.interfaces;

import pl.edu.pwr.concertbooker.exception.custom.EntityNotFoundException;
import pl.edu.pwr.concertbooker.service.event.dto.CreateEventDto;
import pl.edu.pwr.concertbooker.service.event.dto.EventInfoDto;
import pl.edu.pwr.concertbooker.service.event.dto.UpdateEventDto;

import java.util.Collection;

public interface IEventService {
    void createEvent(CreateEventDto eventDto);
    void updateEvent(UpdateEventDto eventDto) throws EntityNotFoundException;
    Collection<EventInfoDto> getAllEvents();
    EventInfoDto getEventById(long id) throws EntityNotFoundException;
    void cancelEventById(long id) throws EntityNotFoundException;
}
