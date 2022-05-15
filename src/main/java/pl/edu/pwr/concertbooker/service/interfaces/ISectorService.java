package pl.edu.pwr.concertbooker.service.interfaces;

import pl.edu.pwr.concertbooker.exception.custom.CannotEditVenueWithExistingEventsException;
import pl.edu.pwr.concertbooker.exception.custom.EntityNotFoundException;
import pl.edu.pwr.concertbooker.model.Sector;
import pl.edu.pwr.concertbooker.service.sector.dto.CreateSectorDto;
import pl.edu.pwr.concertbooker.service.sector.dto.SectorInfoDto;
import pl.edu.pwr.concertbooker.service.sector.dto.UpdateSectorDto;

import java.util.Collection;

public interface ISectorService {
    Sector addSectorForVenue(CreateSectorDto SectorDto) throws EntityNotFoundException, CannotEditVenueWithExistingEventsException;
    void updateSectorForVenue(UpdateSectorDto sectorDto) throws EntityNotFoundException, CannotEditVenueWithExistingEventsException;
    Collection<SectorInfoDto> getAllSectors();
    Collection<SectorInfoDto> getAlLSectorsByVenueId(long venueId) throws EntityNotFoundException;
    SectorInfoDto getSectorByID(long id) throws EntityNotFoundException;
    Sector getSector(long id) throws EntityNotFoundException;
    void deleteSectorById(long id) throws EntityNotFoundException, CannotEditVenueWithExistingEventsException;
}
