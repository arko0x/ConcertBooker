package pl.edu.pwr.concertbooker.service.venue;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.edu.pwr.concertbooker.exception.custom.EntityNotFoundException;
import pl.edu.pwr.concertbooker.model.Venue;
import pl.edu.pwr.concertbooker.repository.VenueRepository;
import pl.edu.pwr.concertbooker.service.event.dto.EventInfoDto;
import pl.edu.pwr.concertbooker.service.interfaces.ISectorService;
import pl.edu.pwr.concertbooker.service.interfaces.IVenueService;
import pl.edu.pwr.concertbooker.service.venue.dto.CreateVenueDto;
import pl.edu.pwr.concertbooker.service.venue.dto.UpdateVenueDto;
import pl.edu.pwr.concertbooker.service.venue.dto.VenueInfoDto;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class VenueService implements IVenueService {
    private VenueRepository venueRepository;
    private ISectorService sectorService;

    @Override
    public void addVenue(CreateVenueDto venueDto) {
        Venue venue = new Venue();
        venue.setName(venueDto.getName());
        venue.setAddress(venueDto.getAddress());
        venueRepository.save(venue);
    }

    @Override
    public void updateVenue(UpdateVenueDto venueDto) throws EntityNotFoundException {
        Optional<Venue> venueOptional = venueRepository.findById(venueDto.getId());
        if (venueOptional.isPresent()) {
            Venue venue = venueOptional.get();
            venue.setName(venueDto.getName());
            venue.setAddress(venueDto.getAddress());

            venueRepository.save(venue);
        } else {
            throw new EntityNotFoundException(venueDto.getId());
        }
    }

    @Override
    public Collection<VenueInfoDto> getAllVenues() {
        return venueRepository.findAll().stream().map(venue -> new VenueInfoDto(venue.getId(), venue.getName(),
                venue.getAddress())).collect(Collectors.toList());
    }

    @Override
    public VenueInfoDto getVenueByID(long id) throws EntityNotFoundException {
        return venueRepository.findById(id).map(venue -> new VenueInfoDto(venue.getId(), venue.getName(),
                venue.getAddress())).orElseThrow(() -> new EntityNotFoundException(id));
    }

    @Override
    public Venue getVenue(long id) throws EntityNotFoundException {
        return venueRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(id));
    }

    @Override
    public void deleteVenueById(long id) throws EntityNotFoundException {
        Optional<Venue> venueOptional = venueRepository.findById(id);
        if (venueOptional.isPresent()) {
            venueRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException(id);
        }
    }
}