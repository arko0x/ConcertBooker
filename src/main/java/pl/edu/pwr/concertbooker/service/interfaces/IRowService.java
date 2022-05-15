package pl.edu.pwr.concertbooker.service.interfaces;

import pl.edu.pwr.concertbooker.exception.custom.CannotEditVenueWithExistingEventsException;
import pl.edu.pwr.concertbooker.exception.custom.EntityNotFoundException;
import pl.edu.pwr.concertbooker.model.Row;

import pl.edu.pwr.concertbooker.service.row.dto.CreateRowDto;
import pl.edu.pwr.concertbooker.service.row.dto.RowInfoDto;
import pl.edu.pwr.concertbooker.service.row.dto.UpdateRowDto;

import java.util.Collection;

public interface IRowService {
    Row addRow(CreateRowDto rowDto) throws EntityNotFoundException, CannotEditVenueWithExistingEventsException;
    void updateRow(UpdateRowDto rowDto) throws EntityNotFoundException, CannotEditVenueWithExistingEventsException;
    Collection<RowInfoDto> getAllRows();
    RowInfoDto getRowByID(long id) throws EntityNotFoundException;
    Row getRow(long id) throws EntityNotFoundException;
    void deleteRowById(long id) throws EntityNotFoundException, CannotEditVenueWithExistingEventsException;
}
