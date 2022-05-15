package pl.edu.pwr.concertbooker.service.ticket.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import pl.edu.pwr.concertbooker.model.enums.TicketType;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@Getter
public class CreateTicketDto {
    @NotNull
    private TicketType type;

    @NotNull
    private Long seatId;

    @NotNull
    private Long eventId;
}
