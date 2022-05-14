package pl.edu.pwr.concertbooker.service.seat.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import pl.edu.pwr.concertbooker.model.enums.SeatType;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@Getter
public class SeatInfoDto {
    private long id;
    private int number;
    private SeatType type;
    private long rowId;
}
