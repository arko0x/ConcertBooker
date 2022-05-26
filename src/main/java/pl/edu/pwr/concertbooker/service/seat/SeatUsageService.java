package pl.edu.pwr.concertbooker.service.seat;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.edu.pwr.concertbooker.repository.SeatRepository;
import pl.edu.pwr.concertbooker.service.interfaces.ISeatServiceUsage;
import pl.edu.pwr.concertbooker.service.seat.dto.SeatInfoDto;

import java.util.Collection;

@Service
@AllArgsConstructor
public class SeatUsageService implements ISeatServiceUsage {
    private SeatRepository seatRepository;
    @Override
    public Collection<SeatInfoDto> getSeatsByRowId(long rowId) {
        return seatRepository.findAll().stream().filter(seat -> seat.getRow().getId() == rowId).map(
                seat -> new SeatInfoDto(seat.getId(), seat.getNumber(), seat.getType(), seat.getRow().getId())
        ).toList();
    }
}
