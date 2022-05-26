package pl.edu.pwr.concertbooker.service.interfaces;

import pl.edu.pwr.concertbooker.service.seat.dto.SeatInfoDto;

import java.util.Collection;

public interface ISeatServiceUsage {
    Collection<SeatInfoDto> getSeatsByRowId(long rowId);
}
