package pl.edu.pwr.concertbooker.service.event;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.edu.pwr.concertbooker.exception.custom.EntityNotFoundException;
import pl.edu.pwr.concertbooker.model.Event;
import pl.edu.pwr.concertbooker.model.Ticket;
import pl.edu.pwr.concertbooker.repository.EventRepository;
import pl.edu.pwr.concertbooker.service.event.dto.CreateEventDto;
import pl.edu.pwr.concertbooker.service.event.dto.EventInfoDto;
import pl.edu.pwr.concertbooker.service.event.dto.UpdateEventDto;
import pl.edu.pwr.concertbooker.service.interfaces.IEventService;
import pl.edu.pwr.concertbooker.service.interfaces.ITicketService;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EventService implements IEventService {
    private EventRepository eventRepository;
    private ITicketService ticketService;

    @Override
    public void createEvent(CreateEventDto eventDto) {
        Event event = new Event();
        event.setName(eventDto.getName());
        event.setDate(eventDto.getDate());
        event.setDescription(eventDto.getDescription());

        eventRepository.save(event);
    }

    @Override
    public void updateEvent(UpdateEventDto eventDto) throws EntityNotFoundException {
        Optional<Event> eventOptional = eventRepository.findById(eventDto.getId());

        if (eventOptional.isEmpty()) {
            throw new EntityNotFoundException(eventDto.getId());
        } else {
            Event event = eventOptional.get();
            event.setName(eventDto.getName());
            event.setDate(eventDto.getDate());
            event.setDescription(eventDto.getDescription());

            eventRepository.save(event);
        }
    }

    @Override
    public Collection<EventInfoDto> getAllEvents() {
        return eventRepository.findAll().stream().map(event -> new EventInfoDto(event.getId(), event.getName(),
                event.getDate(), event.getDescription())).collect(Collectors.toList());
    }

    @Override
    public EventInfoDto getEventById(long id) throws EntityNotFoundException {
        return eventRepository.findById(id).map(event -> new EventInfoDto(event.getId(), event.getName(), event.getDate(),
                event.getDescription())).orElseThrow(() -> new EntityNotFoundException(id));
    }

    @Override
    public void cancelEventById(long id) throws EntityNotFoundException {
        Optional<Event> eventOptional = eventRepository.findById(id);

        if (eventOptional.isEmpty()) {
            throw new EntityNotFoundException(id);
        }

        Collection<Ticket> tickets = ticketService.getTicketsForEventWithId(id);
        sendCancellationInfoToUsers(tickets);
        ticketService.cancelTickets(tickets.stream().map(Ticket::getId).collect(Collectors.toList()));
    }

    private void sendCancellationInfoToUsers(Collection<Ticket> tickets) {
        tickets.forEach(ticket -> {
            // tutaj powinno byc wysylanie maili
        });
    }
}
