package pl.edu.pwr.concertbooker.service.interfaces;

import pl.edu.pwr.concertbooker.exception.custom.EntityNotFoundException;
import pl.edu.pwr.concertbooker.model.Venue;
import pl.edu.pwr.concertbooker.service.venue.dto.CreateVenueDto;
import pl.edu.pwr.concertbooker.service.venue.dto.UpdateVenueDto;
import pl.edu.pwr.concertbooker.service.venue.dto.VenueInfoDto;

import java.util.Collection;

public interface IVenueService {
    void addVenue(CreateVenueDto venueDto);
    void updateVenue(UpdateVenueDto venueDto) throws EntityNotFoundException;
    Collection<VenueInfoDto> getAllVenues();
    VenueInfoDto getVenueByID(long id) throws EntityNotFoundException;
    Venue getVenue(long id) throws EntityNotFoundException;
    void deleteVenueById(long id) throws  EntityNotFoundException;
}
