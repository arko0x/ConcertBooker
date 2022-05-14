package pl.edu.pwr.concertbooker.service.seat.dto;

import lombok.Getter;
import lombok.Setter;
import pl.edu.pwr.concertbooker.model.enums.SeatType;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@Getter
@Setter
public class CreateSeatDto {
    @NotNull
    private int number;
    @NotNull
    private SeatType type;
    @NotNull
    private long rowId;
}
