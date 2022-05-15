package pl.edu.pwr.concertbooker.service.ticket.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import pl.edu.pwr.concertbooker.model.enums.TicketType;

@Getter
@AllArgsConstructor
public class TicketInfoDto {
    private long id;
    private TicketType type;
    private long seatId;
    private long eventId;
    private boolean available;
}
