package pl.edu.pwr.concertbooker.service.interfaces;

import pl.edu.pwr.concertbooker.exception.custom.CannotEditVenueWithExistingEventsException;
import pl.edu.pwr.concertbooker.exception.custom.EntityNotFoundException;
import pl.edu.pwr.concertbooker.model.Seat;
import pl.edu.pwr.concertbooker.service.seat.dto.CreateSeatDto;
import pl.edu.pwr.concertbooker.service.seat.dto.SeatInfoDto;
import pl.edu.pwr.concertbooker.service.seat.dto.UpdateSeatDto;


import java.util.Collection;

public interface ISeatService {
    Seat addSeat(CreateSeatDto seatDto) throws EntityNotFoundException, CannotEditVenueWithExistingEventsException;
    void updateSeat(UpdateSeatDto seatDto) throws EntityNotFoundException, CannotEditVenueWithExistingEventsException;
    Collection<SeatInfoDto> getAllSeats();
    SeatInfoDto getSeatByID(long id) throws EntityNotFoundException;
    Seat getSeat(long id) throws EntityNotFoundException;
    void deleteSeatById(long id) throws EntityNotFoundException, CannotEditVenueWithExistingEventsException;
}
