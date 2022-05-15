package pl.edu.pwr.concertbooker.service.sector;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.edu.pwr.concertbooker.exception.custom.CannotEditVenueWithExistingEventsException;
import pl.edu.pwr.concertbooker.exception.custom.EntityNotFoundException;
import pl.edu.pwr.concertbooker.model.Row;
import pl.edu.pwr.concertbooker.model.Sector;
import pl.edu.pwr.concertbooker.model.Venue;
import pl.edu.pwr.concertbooker.repository.SectorRepository;
import pl.edu.pwr.concertbooker.repository.VenueRepository;
import pl.edu.pwr.concertbooker.service.event.EventService;
import pl.edu.pwr.concertbooker.service.interfaces.IEventService;
import pl.edu.pwr.concertbooker.service.interfaces.ISectorService;
import pl.edu.pwr.concertbooker.service.interfaces.IVenueService;
import pl.edu.pwr.concertbooker.service.interfaces.IVenueUsageService;
import pl.edu.pwr.concertbooker.service.sector.dto.CreateSectorDto;
import pl.edu.pwr.concertbooker.service.sector.dto.SectorInfoDto;
import pl.edu.pwr.concertbooker.service.sector.dto.UpdateSectorDto;

import java.util.Collection;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SectorService implements ISectorService {
    SectorRepository sectorRepository;
    VenueRepository venueRepository;
    IVenueUsageService venueUsageService;

    @Override
    public Sector addSectorForVenue(CreateSectorDto createSectorDto) throws EntityNotFoundException, CannotEditVenueWithExistingEventsException {
        var venue = venueRepository.findById(createSectorDto.getVenueId());
        Sector sector = new Sector();
        if (venue.isPresent()) {
            if (!venueUsageService.getEventsForVenueId(venue.get().getId()).isEmpty()) {
                throw new CannotEditVenueWithExistingEventsException();
            }
            sector.setName(createSectorDto.getSectorName());
            sector.setRowInVenue(createSectorDto.getRowInVenue());
            sector.setColumnInVenue(createSectorDto.getColumnInVenue());
            sector.setVenue(venue.get());
        } else {
            throw new EntityNotFoundException(createSectorDto.getVenueId());
        }

        sectorRepository.save(sector);
        return sector;
    }

    @Override
    public void updateSectorForVenue(UpdateSectorDto sectorDto) throws EntityNotFoundException, CannotEditVenueWithExistingEventsException {
        Optional<Sector> sectorOptional = sectorRepository.findById(sectorDto.getId());
        if (sectorOptional.isPresent()){
            Optional<Venue> venue = venueRepository.findById(sectorOptional.get().getVenue().getId());
            if (venue.isPresent() && !venueUsageService.getEventsForVenueId(venue.get().getId()).isEmpty()) {
                throw new CannotEditVenueWithExistingEventsException();
            }
            Sector sector = sectorOptional.get();
            sector.setName(sectorDto.getSectorName());
            sector.setColumnInVenue(sectorDto.getColumnInVenue());
            sector.setRowInVenue(sectorDto.getColumnInVenue());
            sectorRepository.save(sector);
        }
        else throw new EntityNotFoundException(sectorDto.getId());
    }

    @Override
    public Collection<SectorInfoDto> getAllSectors() {
        return sectorRepository.findAll().stream().map(sector ->
                new SectorInfoDto(sector.getId(), sector.getName(), sector.getRows().size(),
                        sector.getVenue().getId(), sector.getRowInVenue(),
                        sector.getColumnInVenue())).toList();
    }

    @Override
    public Collection<SectorInfoDto> getAlLSectorsByVenueId(long venueId) throws EntityNotFoundException {
        return sectorRepository.findAll().stream()
                .filter(sector -> sector.getVenue().getId() == venueId)
                .map(sector ->
                new SectorInfoDto(sector.getId(), sector.getName(), sector.getRows().size(),
                        sector.getVenue().getId(), sector.getRowInVenue(),
                        sector.getColumnInVenue())).toList();
    }

    @Override
    public SectorInfoDto getSectorByID(long id) throws EntityNotFoundException {
        return sectorRepository.findById(id).map(sector ->
                new SectorInfoDto(sector.getId(), sector.getName(), sector.getRows().size(),
                        sector.getVenue().getId(), sector.getRowInVenue(),
                        sector.getColumnInVenue())).orElseThrow(() -> new EntityNotFoundException(id));
    }

    @Override
    public Sector getSector(long id) throws EntityNotFoundException {
        return sectorRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(id));
    }

    @Override
    public void deleteSectorById(long id) throws EntityNotFoundException, CannotEditVenueWithExistingEventsException {
        Optional<Sector> sectorOptional = sectorRepository.findById(id);
        if (sectorOptional.isPresent()) {
            Optional<Venue> venue = venueRepository.findById(sectorOptional.get().getVenue().getId());
            if (venue.isPresent() && !venueUsageService.getEventsForVenueId(venue.get().getId()).isEmpty()) {
                throw new CannotEditVenueWithExistingEventsException();
            }
            sectorRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException(id);
        }
    }
}
