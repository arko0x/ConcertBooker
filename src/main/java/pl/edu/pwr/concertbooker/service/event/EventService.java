package pl.edu.pwr.concertbooker.service.event;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.edu.pwr.concertbooker.exception.custom.CannotEditEventWithAlreadySoldTicketsException;
import pl.edu.pwr.concertbooker.exception.custom.EntityNotFoundException;
import pl.edu.pwr.concertbooker.exception.custom.NotUserTicketException;
import pl.edu.pwr.concertbooker.model.*;
import pl.edu.pwr.concertbooker.repository.EventRepository;
import pl.edu.pwr.concertbooker.repository.VenueRepository;
import pl.edu.pwr.concertbooker.service.event.dto.CreateEventDto;
import pl.edu.pwr.concertbooker.service.event.dto.EventInfoDto;
import pl.edu.pwr.concertbooker.service.event.dto.EventInfoWithVenueDto;
import pl.edu.pwr.concertbooker.service.event.dto.UpdateEventDto;
import pl.edu.pwr.concertbooker.service.interfaces.IEventService;
import pl.edu.pwr.concertbooker.service.interfaces.ITicketService;
import pl.edu.pwr.concertbooker.service.interfaces.IVenueService;
import pl.edu.pwr.concertbooker.service.ticket.dto.TicketInfoDto;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EventService implements IEventService {
    private EventRepository eventRepository;
    private VenueRepository venueRepository;
    private IVenueService venueService;
    private ITicketService ticketService;

    @Override
    public void createEventWithTickets(CreateEventDto eventDto) throws EntityNotFoundException {
        Event event = new Event();
        event.setName(eventDto.getName());
        event.setDate(eventDto.getDate());
        event.setDescription(eventDto.getDescription());
        event.setArtist(eventDto.getArtist());

        Optional<Venue> venueOptional = venueRepository.findById(eventDto.getVenueId());

        if (venueOptional.isPresent()) {
            event.setVenue(venueOptional.get());
            eventRepository.save(event);
            createTicketsForEvent(event);
        } else {
            throw new EntityNotFoundException(eventDto.getVenueId());
        }
    }

    private void createTicketsForEvent(Event event) throws EntityNotFoundException {
        Venue venue = event.getVenue();
        for (Sector sector : venue.getSectors()) {
            for (Row row : sector.getRows()) {
                for (Seat seat : row.getSeats()) {
                    ticketService.addTicketForEventAndSeat(event, seat.getId());
                }
            }
        }
    }

    @Override
    public void updateEvent(UpdateEventDto eventDto) throws EntityNotFoundException, CannotEditEventWithAlreadySoldTicketsException {
        Optional<Event> eventOptional = eventRepository.findById(eventDto.getId());
        Optional<Venue> venueOptional = venueRepository.findById(eventDto.getVenueId());

        if (eventOptional.isEmpty()) {
            throw new EntityNotFoundException(eventDto.getId());
        } else if (venueOptional.isEmpty()) {
            throw new EntityNotFoundException(eventDto.getVenueId());
        } else {
            Event event = eventOptional.get();
//            if (ticketService.getTicketsForEventWithId(event.getId()).stream().anyMatch(e -> e.getUser() != null)) {
//                throw new CannotEditEventWithAlreadySoldTicketsException();
//            }
            event.setName(eventDto.getName());
            event.setDate(eventDto.getDate());
            event.setDescription(eventDto.getDescription());
            event.setArtist(eventDto.getArtist());

            Collection<TicketInfoDto> tickets = ticketService.getTicketsForEventWithId(event.getId());
            if (tickets != null) {
                sendChangeEventInfoToUsers(tickets, eventDto);
            }
            eventRepository.save(event);
        }
    }

    @Override
    public Collection<EventInfoDto> getAllEvents() {
        return eventRepository.findAll().stream().map(event -> new EventInfoDto(event.getId(), event.getName(),
                event.getDate(), event.getDescription(), event.getArtist(), event.getVenue().getId())).collect(Collectors.toList());
    }

    @Override
    public EventInfoDto getEventById(long id) throws EntityNotFoundException {
        return eventRepository.findById(id).map(event -> new EventInfoDto(event.getId(), event.getName(), event.getDate(),
                event.getDescription(), event.getArtist(), event.getVenue().getId())).orElseThrow(() -> new EntityNotFoundException(id));
    }

    @Override
    public Collection<Event> getEventsForVenueId(long venueId) {
        Optional<Venue> venueOptional = venueRepository.findById(venueId);
        if (venueOptional.isPresent()) {
            return eventRepository.findByVenue(venueOptional.get());
        }
        return List.of();
    }

    @Override
    public Event getEvent(long id) throws EntityNotFoundException {
        Optional<Event> eventOptional = eventRepository.findById(id);
        if (eventOptional.isPresent()) {
            return eventOptional.get();
        }
        throw new EntityNotFoundException(id);
    }

    @Override
    public EventInfoWithVenueDto getEventWithVenue(long id) throws EntityNotFoundException {
        Optional<Event> eventOpt = eventRepository.findById(id);
        if (eventOpt.isEmpty()) throw new EntityNotFoundException(id);
        var event = eventOpt.get();
        var venue = venueService.getVenueWithSeats(event.getVenue().getId());
        var tickets = ticketService.getFullTicketsForEventWithId(event.getId());
        return new EventInfoWithVenueDto(
                event.getId(), event.getName(), event.getDate(), event.getDescription(), event.getArtist(),
                venue, tickets
        );
    }

    @Override
    public void cancelEventById(long id) throws EntityNotFoundException, NotUserTicketException {
        Optional<Event> eventOptional = eventRepository.findById(id);

        if (eventOptional.isEmpty()) {
            throw new EntityNotFoundException(id);
        }

        Collection<TicketInfoDto> tickets = ticketService.getTicketsForEventWithId(id);

        if (tickets != null) {
            sendCancellationInfoToUsers(tickets);
            ticketService.cancelTickets(tickets.stream().map(TicketInfoDto::getId).collect(Collectors.toList()));
        }

        eventRepository.deleteById(id);
    }

    private void sendCancellationInfoToUsers(Collection<TicketInfoDto> tickets) {
        tickets.forEach(ticket -> {
            // tutaj powinno byc wysylanie maili
        });
    }

    private void sendChangeEventInfoToUsers(Collection<TicketInfoDto> tickets, UpdateEventDto updateEventDto) {
        tickets.forEach(ticket -> {
            // tutaj tez jakies maile
        });
    }
}
