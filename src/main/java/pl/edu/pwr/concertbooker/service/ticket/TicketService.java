package pl.edu.pwr.concertbooker.service.ticket;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.edu.pwr.concertbooker.exception.custom.EntityNotFoundException;
import pl.edu.pwr.concertbooker.exception.custom.NotUserTicketException;
import pl.edu.pwr.concertbooker.exception.custom.TicketAlreadyBoughtException;
import pl.edu.pwr.concertbooker.model.Event;
import pl.edu.pwr.concertbooker.model.Seat;
import pl.edu.pwr.concertbooker.model.Ticket;
import pl.edu.pwr.concertbooker.model.enums.TicketType;
import pl.edu.pwr.concertbooker.repository.EventRepository;
import pl.edu.pwr.concertbooker.repository.TicketRepository;
import pl.edu.pwr.concertbooker.security.User;
import pl.edu.pwr.concertbooker.security.UserService;
import pl.edu.pwr.concertbooker.service.interfaces.ISeatService;
import pl.edu.pwr.concertbooker.service.interfaces.ITicketService;
import pl.edu.pwr.concertbooker.service.ticket.dto.CreateTicketDto;
import pl.edu.pwr.concertbooker.service.ticket.dto.TicketInfoDto;
import pl.edu.pwr.concertbooker.service.ticket.dto.TicketInfoFullDto;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TicketService implements ITicketService {
    private TicketRepository ticketRepository;
    private EventRepository eventRepository;
    private ISeatService seatService;
    private UserService userService;

    @Override
    public Collection<TicketInfoDto> getTicketsForEventWithId(long id) throws EntityNotFoundException {
        Optional<Event> event = eventRepository.findById(id);
        if (event.isPresent()) {
            return ticketRepository.findTicketsByEventId(event.get().getId()).stream().map(ticket ->
                    new TicketInfoDto(ticket.getId(), ticket.getType(), ticket.getSeat().getId(), ticket.getEvent().getId(),
                            ticket.getType() == null
                    )).toList();
        } else {
            throw new EntityNotFoundException(id);
        }
    }

    @Override
    public Collection<TicketInfoFullDto> getFullTicketsForEventWithId(long id) throws EntityNotFoundException {
        Optional<Event> event = eventRepository.findById(id);
        if (event.isPresent()) {
            return ticketRepository.findTicketsByEventId(event.get().getId()).stream().map(ticket ->
                    new TicketInfoFullDto(ticket.getId(), ticket.getType(), ticket.getSeat().getId(), ticket.getEvent().getId(),
                            ticket.getType() == null, ticket.getSeat().getNumber(), ticket.getSeat().getRow().getName(), ticket.getSeat().getRow().getSector().getName()
                    )).toList();
        } else {
            throw new EntityNotFoundException(id);
        }
    }

    @Override
    public Ticket addTicket(CreateTicketDto createTicketDto) throws EntityNotFoundException {
        Event event = eventRepository.getOne(createTicketDto.getEventId());
        Seat seat = seatService.getSeat(createTicketDto.getSeatId());

        if (event != null && seat != null) {
            Ticket ticket = new Ticket();
            ticket.setEvent(event);
            ticket.setSeat(seat);
            ticket.setType(createTicketDto.getType());
            ticketRepository.save(ticket);
            return ticket;
        }

        return null;
    }

    @Override
    public Ticket addTicketForEventAndSeat(Event event, long seatId) throws EntityNotFoundException {
        Ticket ticket = new Ticket();
        ticket.setEvent(event);
        ticket.setSeat(seatService.getSeat(seatId));
        ticketRepository.save(ticket);
        return ticket;
    }

    @Override
    public Ticket deleteTicketById(long id) throws EntityNotFoundException, TicketAlreadyBoughtException {
        Optional<Ticket> optionalTicket = ticketRepository.findById(id);

        if (optionalTicket.isPresent()) {
            Ticket ticket = optionalTicket.get();

            if (ticket.getUser() != null) {
                throw new TicketAlreadyBoughtException(id);
            }
            ticketRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException(id);
        }
        return null;
    }

    @Override
    public Ticket buyTicket(long id, TicketType type) throws EntityNotFoundException, NotUserTicketException {
        User user = new User();
        Optional<Ticket> ticketOptional = ticketRepository.findById(id);
        if (ticketOptional.isPresent()) {
            Ticket ticket = ticketOptional.get();
            if (ticket.getUser() == null) {
                ticket.setUser(user);
                ticket.setType(type);
                ticketRepository.save(ticket);
            } else {
                throw new NotUserTicketException();
            }
        } else {
            throw new EntityNotFoundException(id);
        }
        return ticketOptional.orElse(null);
    }

    @Override
    public void cancelTickets(List<Long> ids) throws NotUserTicketException {
        User user = new User();
        Collection<Ticket> tickets = ticketRepository.findTicketsByUserId(user.getId());
        if (tickets.stream().map(Ticket::getId).toList().containsAll(ids)) {
            List<Ticket> ticketsToCancel = tickets.stream().filter(ticket -> ids.contains(ticket.getId())).toList();
            for (int i = 0; i < ticketsToCancel.size(); i++) {
                ticketsToCancel.get(i).setType(null);
                ticketsToCancel.get(i).setUser(null);
            }
            ticketRepository.saveAll(ticketsToCancel);
        } else {
            throw new NotUserTicketException();
        }
    }
}
