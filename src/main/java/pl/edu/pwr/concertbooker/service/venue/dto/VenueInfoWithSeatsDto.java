package pl.edu.pwr.concertbooker.service.venue.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.edu.pwr.concertbooker.model.Sector;
import pl.edu.pwr.concertbooker.service.sector.dto.SectorInfoWithRowsDto;

import java.util.Collection;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class VenueInfoWithSeatsDto {
    private long id;
    private String name;
    private String address;
    private Collection<SectorInfoWithRowsDto> sectors;


}
