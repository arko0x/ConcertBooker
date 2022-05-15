package pl.edu.pwr.concertbooker.service.ticket.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import pl.edu.pwr.concertbooker.model.enums.TicketType;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@Getter
public class UpdateTicketDto {
    @NotNull
    private Long id;

    @NotNull
    private TicketType type;

    @NotNull
    private Long seatId;

    @NotNull
    private Long eventId;

    @NotNull
    private long userId;
}
