package pl.edu.pwr.concertbooker.service.sector;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.edu.pwr.concertbooker.exception.custom.EntityNotFoundException;
import pl.edu.pwr.concertbooker.model.Sector;
import pl.edu.pwr.concertbooker.model.Venue;
import pl.edu.pwr.concertbooker.repository.SectorRepository;
import pl.edu.pwr.concertbooker.service.interfaces.ISectorService;
import pl.edu.pwr.concertbooker.service.interfaces.IVenueService;
import pl.edu.pwr.concertbooker.service.sector.dto.CreateSectorDto;
import pl.edu.pwr.concertbooker.service.sector.dto.SectorInfoDto;
import pl.edu.pwr.concertbooker.service.sector.dto.UpdateSectorDto;

import java.util.Collection;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SectorService implements ISectorService {
    SectorRepository sectorRepository;
    IVenueService venueService;

    @Override
    public void addSectorForVenue(CreateSectorDto createSectorDto) throws EntityNotFoundException {
        var venue = venueService.getVenue(createSectorDto.getVenueId());
        Sector sector = new Sector();
        sector.setName(createSectorDto.getSectorName());
        sector.setRowInVenue(createSectorDto.getRowInVenue());
        sector.setColumnInVenue(createSectorDto.getColumnInVenue());
        sector.setVenue(venue);

        sectorRepository.save(sector);
    }

    @Override
    public void updateSectorForVenue(UpdateSectorDto sectorDto) throws EntityNotFoundException {
        Optional<Sector> sectorOptional = sectorRepository.findById(sectorDto.getId());
        if (sectorOptional.isPresent()){
            Sector sector = sectorOptional.get();
            sector.setName(sectorDto.getSectorName());
            sector.setColumnInVenue(sectorDto.getColumnInVenue());
            sector.setRowInVenue(sectorDto.getColumnInVenue());
        }
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
    public void deleteVenueById(long id) throws EntityNotFoundException {
        Optional<Sector> sectorOptional = sectorRepository.findById(id);
        if (sectorOptional.isPresent()) {
            sectorRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException(id);
        }
    }
}
