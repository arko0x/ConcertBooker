package pl.edu.pwr.concertbooker.service.interfaces;

import pl.edu.pwr.concertbooker.service.sector.dto.CreateSectorDto;

public interface ISectorService {
    void addSectorForVenue(CreateSectorDto createSectorDto);
}
