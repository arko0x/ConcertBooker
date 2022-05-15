package pl.edu.pwr.concertbooker.service.interfaces;

import pl.edu.pwr.concertbooker.exception.custom.EntityNotFoundException;
import pl.edu.pwr.concertbooker.exception.custom.NotUserTicketException;
import pl.edu.pwr.concertbooker.exception.custom.TicketAlreadyBoughtException;
import pl.edu.pwr.concertbooker.model.Event;
import pl.edu.pwr.concertbooker.model.Ticket;
import pl.edu.pwr.concertbooker.model.enums.TicketType;
import pl.edu.pwr.concertbooker.service.ticket.dto.CreateTicketDto;

import java.util.Collection;
import java.util.List;

public interface ITicketService {
    Collection<Ticket> getTicketsForEventWithId(long id) throws EntityNotFoundException;
    Ticket addTicket(CreateTicketDto createTicketDto) throws EntityNotFoundException;
    Ticket addTicketForEventAndSeat(Event event, long seatId) throws EntityNotFoundException;

    Ticket deleteTicketById(long id) throws EntityNotFoundException, TicketAlreadyBoughtException;
    Ticket buyTicket(long id, TicketType type) throws EntityNotFoundException, NotUserTicketException;
    void cancelTickets(List<Long> ids) throws NotUserTicketException;
}
