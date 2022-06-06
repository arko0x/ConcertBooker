package pl.edu.pwr.concertbooker.api;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.edu.pwr.concertbooker.exception.custom.CannotEditVenueWithExistingEventsException;
import pl.edu.pwr.concertbooker.exception.custom.EntityNotFoundException;
import pl.edu.pwr.concertbooker.model.Row;
import pl.edu.pwr.concertbooker.service.interfaces.IRowService;
import pl.edu.pwr.concertbooker.service.row.dto.CreateRowDto;
import pl.edu.pwr.concertbooker.service.row.dto.RowInfoDto;
import pl.edu.pwr.concertbooker.service.row.dto.UpdateRowDto;

import javax.validation.Valid;
import java.net.URI;
import java.util.Collection;

@RestController
@RequestMapping("api/v1/row")
@AllArgsConstructor
public class RowController {
    private IRowService rowService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<RowInfoDto> getRowById(@PathVariable long id) throws EntityNotFoundException {
        return ResponseEntity.ok(rowService.getRowByID(id));
    }

    @GetMapping(value = "/all")
    public Collection<RowInfoDto> getAllRows() {
        return rowService.getAllRows();
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<RowInfoDto> addRow(@RequestBody @Valid CreateRowDto rowDto) throws EntityNotFoundException, CannotEditVenueWithExistingEventsException {
        Row row = rowService.addRow(rowDto);
        RowInfoDto rowInfoDto = new RowInfoDto(row.getId(), rowDto.getName(), rowDto.getSectorId());
        return ResponseEntity.created(URI.create("/api/v1/row" + row.getId())).body(rowInfoDto);
    }

    @PutMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<RowInfoDto> updateRow(@RequestBody @Valid UpdateRowDto rowDto) throws EntityNotFoundException, CannotEditVenueWithExistingEventsException {
        rowService.updateRow(rowDto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{id}")
    public void deleteRowById(@PathVariable long id) throws EntityNotFoundException, CannotEditVenueWithExistingEventsException {
        rowService.deleteRowById(id);
    }
}
