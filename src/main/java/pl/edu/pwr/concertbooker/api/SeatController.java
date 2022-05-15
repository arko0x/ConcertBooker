package pl.edu.pwr.concertbooker.api;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.pwr.concertbooker.exception.custom.CannotEditVenueWithExistingEventsException;
import pl.edu.pwr.concertbooker.exception.custom.EntityNotFoundException;
import pl.edu.pwr.concertbooker.model.Seat;
import pl.edu.pwr.concertbooker.service.interfaces.ISeatService;
import pl.edu.pwr.concertbooker.service.seat.dto.CreateSeatDto;
import pl.edu.pwr.concertbooker.service.seat.dto.SeatInfoDto;
import pl.edu.pwr.concertbooker.service.seat.dto.UpdateSeatDto;

import javax.validation.Valid;
import java.util.Collection;

@RestController
@RequestMapping("api/v1/seat")
@AllArgsConstructor
public class SeatController {
    private ISeatService seatService;

    @PostMapping
    public ResponseEntity<SeatInfoDto> addSeat(@RequestBody @Valid CreateSeatDto seatDto) throws EntityNotFoundException, CannotEditVenueWithExistingEventsException {
        Seat seat = seatService.addSeat(seatDto);
        return ResponseEntity.ok(new SeatInfoDto(seat.getId(), seat.getNumber(), seat.getType(), seatDto.getRowId()));
    }

    @PutMapping
    public ResponseEntity<SeatInfoDto> updateSeat(@RequestBody @Valid UpdateSeatDto seatDto) throws EntityNotFoundException, CannotEditVenueWithExistingEventsException {
        seatService.updateSeat(seatDto);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/all")
    public Collection<SeatInfoDto> getAllSeats() {
        return seatService.getAllSeats();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<SeatInfoDto> getSeatById(@PathVariable long id) throws EntityNotFoundException {
        return ResponseEntity.ok(seatService.getSeatByID(id));
    }

    @DeleteMapping(value = "/{id}")
    public void deleteSeatById(@PathVariable long id) throws EntityNotFoundException, CannotEditVenueWithExistingEventsException {
        seatService.deleteSeatById(id);
    }
}
