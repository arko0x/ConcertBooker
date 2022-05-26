package pl.edu.pwr.concertbooker.api;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.pwr.concertbooker.exception.custom.EntityNotFoundException;
import pl.edu.pwr.concertbooker.exception.custom.NotUserTicketException;
import pl.edu.pwr.concertbooker.exception.custom.TicketAlreadyBoughtException;
import pl.edu.pwr.concertbooker.model.Ticket;
import pl.edu.pwr.concertbooker.model.enums.TicketType;
import pl.edu.pwr.concertbooker.service.interfaces.ITicketService;
import pl.edu.pwr.concertbooker.service.ticket.dto.CreateTicketDto;
import pl.edu.pwr.concertbooker.service.ticket.dto.TicketInfoDto;

import javax.validation.Valid;
import java.net.URI;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/ticket")
@AllArgsConstructor
public class TicketController {
    private ITicketService ticketService;

    @GetMapping
    public Collection<TicketInfoDto> getTicketsForEventWithId(@RequestParam long eventId) throws EntityNotFoundException {
        return ticketService.getTicketsForEventWithId(eventId);
    }

    @PostMapping("/buy/{id}")
    public ResponseEntity<TicketInfoDto> buyTicket(@PathVariable long id, @RequestParam TicketType ticketType) throws EntityNotFoundException, NotUserTicketException {
        Ticket ticket = ticketService.buyTicket(id, ticketType);
        return ResponseEntity.ok(new TicketInfoDto(ticket.getId(), ticket.getType(), ticket.getSeat().getId(),
                ticket.getEvent().getId(), ticket.getUser() == null));
    }

    @PostMapping("/cancel")
    void cancelTickets(@RequestParam Long[] ids) throws NotUserTicketException {
        ticketService.cancelTickets(List.of(ids));
    }
}
