package pl.edu.pwr.concertbooker.service.row;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pl.edu.pwr.concertbooker.repository.RowRepository;
import pl.edu.pwr.concertbooker.service.interfaces.IRowServiceUsage;
import pl.edu.pwr.concertbooker.service.row.dto.RowInfoDto;
import pl.edu.pwr.concertbooker.service.row.dto.RowInfoWithSeatsDto;
import pl.edu.pwr.concertbooker.service.seat.SeatUsageService;

import java.util.Collection;

@Service
@AllArgsConstructor
public class RowServiceUsage implements IRowServiceUsage {
    private RowRepository rowRepository;
    private SeatUsageService seatUsageService;
    @Override
    public Collection<RowInfoWithSeatsDto> getRowsBySectorId(long sectorId) {
        return rowRepository.findAll().stream().filter(row -> row.getSector().getId() == sectorId).map(
                row -> new RowInfoWithSeatsDto(row.getId(), row.getName(), row.getSector().getId(),
                        seatUsageService.getSeatsByRowId(row.getId()) )
        ).toList();
    }
}
