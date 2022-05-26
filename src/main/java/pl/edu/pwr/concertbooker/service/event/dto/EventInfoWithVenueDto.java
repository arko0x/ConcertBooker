package pl.edu.pwr.concertbooker.service.event.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import pl.edu.pwr.concertbooker.model.Ticket;
import pl.edu.pwr.concertbooker.service.ticket.dto.TicketInfoDto;
import pl.edu.pwr.concertbooker.service.venue.dto.VenueInfoWithSeatsDto;

import java.time.LocalDateTime;
import java.util.Collection;

@AllArgsConstructor
@Getter
public class EventInfoWithVenueDto {
    private long id;
    private String name;
    private LocalDateTime date;
    private String description;
    private String artist;
    private VenueInfoWithSeatsDto venue;
    private Collection<TicketInfoDto> tickets;
}
