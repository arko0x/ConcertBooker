package pl.edu.pwr.concertbooker.api;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
import pl.edu.pwr.concertbooker.service.venue.dto.VenueInfoWithSeatsDto;

import javax.validation.Valid;
import java.util.Collection;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/venue")
@AllArgsConstructor
public class VenueController {
    private IVenueService venueService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<VenueInfoDto> addVenue(@RequestBody @Valid CreateVenueDto venueDto) throws EntityNotFoundException, CannotEditVenueWithExistingEventsException {
        Venue venue = venueService.addVenue(venueDto);
        return ResponseEntity.ok(new VenueInfoDto(venue.getId(), venue.getName(), venue.getAddress()));
    }

    @PutMapping
    @PreAuthorize("hasRole('ADMIN')")
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

    @GetMapping("/full/{id}")
    public ResponseEntity<VenueInfoWithSeatsDto> getVenueWithSectorsById(@PathVariable long id) throws EntityNotFoundException {
        return ResponseEntity.ok(venueService.getVenueWithSeats(id));
    }



    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteSeatById(@PathVariable long id) throws EntityNotFoundException, CannotEditVenueWithExistingEventsException {
        venueService.deleteVenueById(id);
    }
}
