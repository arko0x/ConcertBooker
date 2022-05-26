package pl.edu.pwr.concertbooker.service.row.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.edu.pwr.concertbooker.model.Seat;
import pl.edu.pwr.concertbooker.service.seat.dto.SeatInfoDto;

import java.util.Collection;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class RowInfoWithSeatsDto {
    private Long id;
    private String name;
    private Long sectorId;
    private Collection<SeatInfoDto> seats;

}
