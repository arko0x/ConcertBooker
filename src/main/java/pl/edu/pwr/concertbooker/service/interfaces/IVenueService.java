package pl.edu.pwr.concertbooker.service.interfaces;

import pl.edu.pwr.concertbooker.exception.custom.CannotEditVenueWithExistingEventsException;
import pl.edu.pwr.concertbooker.exception.custom.EntityNotFoundException;
import pl.edu.pwr.concertbooker.model.Venue;
import pl.edu.pwr.concertbooker.service.venue.dto.CreateVenueDto;
import pl.edu.pwr.concertbooker.service.venue.dto.UpdateVenueDto;
import pl.edu.pwr.concertbooker.service.venue.dto.VenueInfoDto;
import pl.edu.pwr.concertbooker.service.venue.dto.VenueInfoWithSeatsDto;

import java.util.Collection;

public interface IVenueService {
    Venue addVenue(CreateVenueDto venueDto) throws CannotEditVenueWithExistingEventsException, EntityNotFoundException;
    void updateVenue(UpdateVenueDto venueDto) throws EntityNotFoundException, CannotEditVenueWithExistingEventsException;
    Collection<VenueInfoDto> getAllVenues();
    VenueInfoDto getVenueByID(long id) throws EntityNotFoundException;
    VenueInfoWithSeatsDto getVenueWithSeats(long id) throws EntityNotFoundException;
    void deleteVenueById(long id) throws EntityNotFoundException, CannotEditVenueWithExistingEventsException;
}
