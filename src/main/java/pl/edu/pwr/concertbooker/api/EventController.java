package pl.edu.pwr.concertbooker.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.pwr.concertbooker.exception.custom.CannotEditEventWithAlreadySoldTicketsException;
import pl.edu.pwr.concertbooker.exception.custom.EntityNotFoundException;
import pl.edu.pwr.concertbooker.exception.custom.NotUserTicketException;
import pl.edu.pwr.concertbooker.service.event.dto.CreateEventDto;
import pl.edu.pwr.concertbooker.service.event.dto.EventInfoDto;
import pl.edu.pwr.concertbooker.service.event.dto.UpdateEventDto;
import pl.edu.pwr.concertbooker.service.interfaces.IEventService;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/event")
public class EventController {
    private final IEventService eventService;

    public EventController(IEventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventInfoDto> getEventWithId(@PathVariable long id) throws EntityNotFoundException {
        EventInfoDto eventInfoDto = eventService.getEventById(id);
        return ResponseEntity.of(Optional.of(eventInfoDto));
    }

    @GetMapping("/all")
    public Collection<EventInfoDto> getEvents() {
        Collection<EventInfoDto> events = eventService.getAllEvents();
        return events;
    }

    @PostMapping
    public ResponseEntity<CreateEventDto> createEvent(@RequestBody @Valid CreateEventDto createEventDto) throws EntityNotFoundException {
        eventService.createEventWithTickets(createEventDto);
        return ResponseEntity.ok(createEventDto);
    }

    @PutMapping
    public ResponseEntity<UpdateEventDto> updateEvent(@RequestBody @Valid UpdateEventDto updateEventDto) throws EntityNotFoundException, CannotEditEventWithAlreadySoldTicketsException {
        eventService.updateEvent(updateEventDto);
        return ResponseEntity.accepted().body(updateEventDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> cancelEvent(@PathVariable long id) throws EntityNotFoundException, NotUserTicketException {
        eventService.cancelEventById(id);
        return ResponseEntity.ok("Event with id " + id + " cancalled");
    }
}
