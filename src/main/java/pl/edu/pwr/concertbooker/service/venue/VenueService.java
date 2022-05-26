package pl.edu.pwr.concertbooker.service.venue;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.edu.pwr.concertbooker.exception.custom.CannotEditVenueWithExistingEventsException;
import pl.edu.pwr.concertbooker.exception.custom.EntityNotFoundException;
import pl.edu.pwr.concertbooker.model.Venue;
import pl.edu.pwr.concertbooker.model.enums.SeatType;
import pl.edu.pwr.concertbooker.repository.VenueRepository;
import pl.edu.pwr.concertbooker.service.event.dto.EventInfoDto;
import pl.edu.pwr.concertbooker.service.interfaces.*;
import pl.edu.pwr.concertbooker.service.row.dto.CreateRowDto;
import pl.edu.pwr.concertbooker.service.seat.dto.CreateSeatDto;
import pl.edu.pwr.concertbooker.service.sector.dto.CreateSectorDto;
import pl.edu.pwr.concertbooker.service.venue.dto.CreateVenueDto;
import pl.edu.pwr.concertbooker.service.venue.dto.UpdateVenueDto;
import pl.edu.pwr.concertbooker.service.venue.dto.VenueInfoDto;
import pl.edu.pwr.concertbooker.service.venue.dto.VenueInfoWithSeatsDto;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class VenueService implements IVenueService {
    private VenueRepository venueRepository;
    private ISectorService sectorService;
    private IVenueUsageService venueUsageService;
    private IRowService rowService;
    private ISeatService seatService;

    @Override
    public Venue addVenue(CreateVenueDto venueDto) throws CannotEditVenueWithExistingEventsException, EntityNotFoundException {
        Venue venue = new Venue();
        venue.setName(venueDto.getName());
        venue.setAddress(venueDto.getAddress());
        venue.setSeatPattern(venueDto.getSeatPattern());
        var savedVenue = venueRepository.save(venue);
        var sectorRows = venueDto.getSeatPattern().split("/");
        var sectors = Arrays.stream(sectorRows).map(sec -> sec.split(",")).toList();
        for (int i = 0; i < sectors.size(); i++){
            for (int j = 0; j < sectors.get(i).length; j++){
                var sectorString = sectors.get(i)[j].substring(0, sectors.get(i)[j].length()-1).split("\\(");
                CreateSectorDto sectorDto = new CreateSectorDto();
                sectorDto.setRowInVenue(i);
                sectorDto.setColumnInVenue(j);
                sectorDto.setVenueId(savedVenue.getId());
                sectorDto.setSectorName(sectorString[0]);
                var addedSector = sectorService.addSectorForVenue(sectorDto);
                var boundaries = sectorString[1].split("x");
                var rows = Integer.parseInt(boundaries[0]);
                var columns = Integer.parseInt(boundaries[1]);
                for (int k = 0; k < rows; k++){
                    CreateRowDto rowDto = new CreateRowDto(String.valueOf(k+1), addedSector.getId());
                    var row = rowService.addRow(rowDto);
                    for (int l = 0; l < columns; l++){
                        CreateSeatDto seatDto = new CreateSeatDto(l+1, SeatType.SEATED, row.getId());
                        seatService.addSeat(seatDto);
                    }
                }
            }
        }

        return venue;
    }


    @Override
    public void updateVenue(UpdateVenueDto venueDto) throws EntityNotFoundException, CannotEditVenueWithExistingEventsException {
        Optional<Venue> venueOptional = venueRepository.findById(venueDto.getId());
        if (venueOptional.isPresent()) {
            Venue venue = venueOptional.get();
            checkIfEventsDoesNotExist(venue);
            venue.setName(venueDto.getName());
            venue.setAddress(venueDto.getAddress());
            venueRepository.save(venue);
        } else {
            throw new EntityNotFoundException(venueDto.getId());
        }
    }

    private void checkIfEventsDoesNotExist(Venue venue) throws CannotEditVenueWithExistingEventsException {
        if (!venueUsageService.getEventsForVenueId(venue.getId()).isEmpty()) {
            throw new CannotEditVenueWithExistingEventsException();
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
    public VenueInfoWithSeatsDto getVenueWithSeats(long id) throws EntityNotFoundException {
        return venueRepository.findById(id).map(venue ->
                {
                    try {
                        return new VenueInfoWithSeatsDto(venue.getId(), venue.getName(), venue.getAddress(),
                                sectorService.getAlLSectorsByVenueWithRowsId(venue.getId()));
                    } catch (EntityNotFoundException e) {
                        e.printStackTrace();
                    }
                    return null;
                }
                ).orElseThrow(() -> new EntityNotFoundException(id));


    }

    @Override
    public void deleteVenueById(long id) throws EntityNotFoundException, CannotEditVenueWithExistingEventsException {
        Optional<Venue> venueOptional = venueRepository.findById(id);
        if (venueOptional.isPresent()) {
            checkIfEventsDoesNotExist(venueOptional.get());
            venueRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException(id);
        }
    }
}
