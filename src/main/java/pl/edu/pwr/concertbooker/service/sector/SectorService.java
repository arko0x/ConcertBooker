package pl.edu.pwr.concertbooker.service.sector;

import pl.edu.pwr.concertbooker.service.interfaces.ISectorService;
import pl.edu.pwr.concertbooker.service.interfaces.IVenueService;
import pl.edu.pwr.concertbooker.service.sector.dto.CreateSectorDto;

public class SectorService implements ISectorService {
    private final IVenueService venueService;

    public SectorService(IVenueService venueService) {
        this.venueService = venueService;
    }

    @Override
    public void addSectorForVenue(CreateSectorDto createSectorDto) {

    }
}
