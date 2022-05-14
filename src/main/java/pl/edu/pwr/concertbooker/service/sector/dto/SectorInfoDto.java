package pl.edu.pwr.concertbooker.service.sector.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SectorInfoDto {
    private long id;
    private String sectorName;
    private int numberOfRows;
    private long venueId;
    private int rowInVenue;
    private int columnInVenue;
}
