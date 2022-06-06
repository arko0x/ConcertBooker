package pl.edu.pwr.concertbooker.api;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.edu.pwr.concertbooker.exception.custom.CannotEditVenueWithExistingEventsException;
import pl.edu.pwr.concertbooker.exception.custom.EntityNotFoundException;
import pl.edu.pwr.concertbooker.model.Sector;
import pl.edu.pwr.concertbooker.service.interfaces.ISectorService;
import pl.edu.pwr.concertbooker.service.sector.dto.CreateSectorDto;
import pl.edu.pwr.concertbooker.service.sector.dto.SectorInfoDto;
import pl.edu.pwr.concertbooker.service.sector.dto.UpdateSectorDto;

import javax.validation.Valid;
import java.util.Collection;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/sector")
public class SectorController {
    private ISectorService sectorService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<SectorInfoDto> addSector(@RequestBody @Valid CreateSectorDto sectorDto) throws EntityNotFoundException, CannotEditVenueWithExistingEventsException {
        Sector sector = sectorService.addSectorForVenue(sectorDto);
        return ResponseEntity.ok(new SectorInfoDto(sector.getId(), sectorDto.getSectorName(), sector.getRows() != null ? sector.getRows().size() : 0,
                sectorDto.getVenueId(), sectorDto.getRowInVenue(), sectorDto.getColumnInVenue()));
    }

    @PutMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<SectorInfoDto> updateSectors(@RequestBody @Valid UpdateSectorDto sectorDto) throws EntityNotFoundException, CannotEditVenueWithExistingEventsException {
        sectorService.updateSectorForVenue(sectorDto);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/all")
    public Collection<SectorInfoDto> getAllSectors() {
        return sectorService.getAllSectors();
    }

    @GetMapping("/venue/{venueId}")
    public Collection<SectorInfoDto> getAllSectorsByVenueId(@PathVariable long venueId) throws EntityNotFoundException {
        return sectorService.getAlLSectorsByVenueId(venueId);
    }
    @GetMapping("/{id}")
    public ResponseEntity<SectorInfoDto> getSectorById(@PathVariable long id) throws EntityNotFoundException {
        return ResponseEntity.ok(sectorService.getSectorByID(id));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteSeatById(@PathVariable long id) throws EntityNotFoundException, CannotEditVenueWithExistingEventsException {
        sectorService.deleteSectorById(id);
    }
}
