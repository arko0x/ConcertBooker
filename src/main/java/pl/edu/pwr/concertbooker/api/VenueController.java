package pl.edu.pwr.concertbooker.api;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.pwr.concertbooker.exception.custom.CannotEditVenueWithExistingEventsException;
import pl.edu.pwr.concertbooker.exception.custom.EntityNotFoundException;
import pl.edu.pwr.concertbooker.model.Sector;
import pl.edu.pwr.concertbooker.model.Venue;
import pl.edu.pwr.concertbooker.service.interfaces.IVenueService;
import pl.edu.pwr.concertbooker.service.sector.dto.CreateSectorDto;
import pl.edu.pwr.concertbooker.service.sector.dto.SectorInfoDto;
import pl.edu.pwr.concertbooker.service.sector.dto.UpdateSectorDto;
import pl.edu.pwr.concertbooker.service.venue.dto.CreateVenueDto;
import pl.edu.pwr.concertbooker.service.venue.dto.UpdateVenueDto;
import pl.edu.pwr.concertbooker.service.venue.dto.VenueInfoDto;

import javax.validation.Valid;
import java.util.Collection;

@RestController
@RequestMapping("/api/v1/venue")
@AllArgsConstructor
public class VenueController {
    private IVenueService venueService;

    @PostMapping
    public ResponseEntity<VenueInfoDto> addVenue(@RequestBody @Valid CreateVenueDto venueDto) throws EntityNotFoundException {
        Venue venue = venueService.addVenue(venueDto);
        return ResponseEntity.ok(new VenueInfoDto(venue.getId(), venue.getName(), venue.getAddress()));
    }

    @PutMapping
    public ResponseEntity<VenueInfoDto> updateVenue(@RequestBody @Valid UpdateVenueDto venueDto) throws EntityNotFoundException, CannotEditVenueWithExistingEventsException {
        venueService.updateVenue(venueDto);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/all")
    public Collection<VenueInfoDto> getAllVenues() {
        return venueService.getAllVenues();
    }

    @GetMapping("/{id}")
    public ResponseEntity<VenueInfoDto> getVenueById(@PathVariable long id) throws EntityNotFoundException {
        return ResponseEntity.ok(venueService.getVenueByID(id));
    }

    @DeleteMapping("/{id}")
    public void deleteSeatById(@PathVariable long id) throws EntityNotFoundException, CannotEditVenueWithExistingEventsException {
        venueService.deleteVenueById(id);
    }
}
