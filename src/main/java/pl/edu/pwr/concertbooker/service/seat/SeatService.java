package pl.edu.pwr.concertbooker.service.seat;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.edu.pwr.concertbooker.exception.custom.CannotEditVenueWithExistingEventsException;
import pl.edu.pwr.concertbooker.exception.custom.EntityNotFoundException;
import pl.edu.pwr.concertbooker.model.Seat;
import pl.edu.pwr.concertbooker.model.Ticket;
import pl.edu.pwr.concertbooker.repository.SeatRepository;
import pl.edu.pwr.concertbooker.repository.TicketRepository;
import pl.edu.pwr.concertbooker.service.interfaces.*;
import pl.edu.pwr.concertbooker.service.seat.dto.CreateSeatDto;
import pl.edu.pwr.concertbooker.service.seat.dto.SeatInfoDto;
import pl.edu.pwr.concertbooker.service.seat.dto.UpdateSeatDto;

import java.util.Collection;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SeatService implements ISeatService {
    SeatRepository seatRepository;
    TicketRepository ticketRepository;
    IRowService rowService;
    ISectorService sectorService;
    IVenueService venueService;
    IVenueUsageService venueUsageService;

    @Override
    public Seat addSeat(CreateSeatDto seatDto) throws EntityNotFoundException, CannotEditVenueWithExistingEventsException {
        var row = rowService.getRow(seatDto.getRowId());
        var sector = sectorService.getSector(row.getSector().getId());
        var venue = venueService.getVenue(sector.getVenue().getId());
        if (!venueUsageService.getEventsForVenueId(venue.getId()).isEmpty()) {
            throw new CannotEditVenueWithExistingEventsException();
        }
        Seat seat = new Seat();
        seat.setNumber(seatDto.getNumber());
        seat.setType(seatDto.getType());
        seat.setRow(row);

        seatRepository.save(seat);
        return seat;
    }

    @Override
    public void updateSeat(UpdateSeatDto seatDto) throws EntityNotFoundException, CannotEditVenueWithExistingEventsException {
        var row = rowService.getRow(seatDto.getRowId());
        var sector = sectorService.getSector(row.getSector().getId());
        var venue = venueService.getVenue(sector.getVenue().getId());
        if (!venueUsageService.getEventsForVenueId(venue.getId()).isEmpty()) {
            throw new CannotEditVenueWithExistingEventsException();
        }
        Optional<Seat> seatOptional = seatRepository.findById(seatDto.getId());
        if (seatOptional.isPresent()){
            Seat seat = seatOptional.get();
            seat.setNumber(seatDto.getNumber());
            seat.setType(seatDto.getType());
            seatRepository.save(seat);
        }
        else {
            throw new EntityNotFoundException(seatDto.getId());
        }
    }

    @Override
    public Collection<SeatInfoDto> getAllSeats() {
         return seatRepository.findAll().stream().map(seat ->
                new SeatInfoDto(seat.getId(), seat.getNumber(), seat.getType(), seat.getRow().getId())).toList();

    }

    @Override
    public SeatInfoDto getSeatByID(long id) throws EntityNotFoundException {
        return seatRepository.findById(id).map(seat ->
                new SeatInfoDto(seat.getId(), seat.getNumber(), seat.getType(), seat.getRow().getId()))
                .orElseThrow(() -> new EntityNotFoundException(id));
    }

    @Override
    public Seat getSeat(long id) throws EntityNotFoundException {
        return seatRepository.findById(id)  .orElseThrow(() -> new EntityNotFoundException(id));
    }

    @Override
    public void deleteSeatById(long id) throws EntityNotFoundException, CannotEditVenueWithExistingEventsException {
        Optional<Seat> seatOptional = seatRepository.findById(id);
        if (seatOptional.isPresent()) {
            var row = rowService.getRow(seatOptional.get().getRow().getId());
            var sector = sectorService.getSector(row.getSector().getId());
            var venue = venueService.getVenue(sector.getVenue().getId());
            if (!venueUsageService.getEventsForVenueId(venue.getId()).isEmpty()) {
                throw new CannotEditVenueWithExistingEventsException();
            }
            seatRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException(id);
        }
    }
}
