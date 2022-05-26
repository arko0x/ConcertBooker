package pl.edu.pwr.concertbooker.service.sector.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import pl.edu.pwr.concertbooker.service.row.dto.RowInfoWithSeatsDto;

import java.util.Collection;

@Getter
@AllArgsConstructor
public class SectorInfoWithRowsDto {
    private long id;
    private String sectorName;
    private int numberOfRows;
    private long venueId;
    private int rowInVenue;
    private int columnInVenue;
    private Collection<RowInfoWithSeatsDto> rows;
}
