package pl.edu.pwr.concertbooker.service.row;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.edu.pwr.concertbooker.exception.custom.CannotEditVenueWithExistingEventsException;
import pl.edu.pwr.concertbooker.exception.custom.EntityNotFoundException;
import pl.edu.pwr.concertbooker.model.Row;
import pl.edu.pwr.concertbooker.model.Sector;
import pl.edu.pwr.concertbooker.repository.RowRepository;
import pl.edu.pwr.concertbooker.service.interfaces.*;
import pl.edu.pwr.concertbooker.service.row.dto.CreateRowDto;
import pl.edu.pwr.concertbooker.service.row.dto.RowInfoDto;
import pl.edu.pwr.concertbooker.service.row.dto.UpdateRowDto;

import java.util.Collection;
import java.util.Optional;

@AllArgsConstructor
@Service
public class RowService implements IRowService {
    RowRepository rowRepository;
    ISectorService sectorService;
    IVenueService venueService;
    IVenueUsageService venueUsageService;
    @Override
    public Row addRow(CreateRowDto rowDto) throws EntityNotFoundException, CannotEditVenueWithExistingEventsException {
        var sector = sectorService.getSector(rowDto.getSectorId());
        var venue = venueService.getVenue(sector.getVenue().getId());
        if (!venueUsageService.getEventsForVenueId(venue.getId()).isEmpty()) {
            throw new CannotEditVenueWithExistingEventsException();
        }
        Row row = new Row();
        row.setName(rowDto.getName());
        row.setSector(sector);

        rowRepository.save(row);
        return row;
    }

    @Override
    public void updateRow(UpdateRowDto rowDto) throws EntityNotFoundException, CannotEditVenueWithExistingEventsException {
        var sector = sectorService.getSector(rowDto.getSectorId());
        var venue = venueService.getVenue(sector.getVenue().getId());
        if (!venueUsageService.getEventsForVenueId(venue.getId()).isEmpty()) {
            throw new CannotEditVenueWithExistingEventsException();
        }
        Optional<Row> rowOptional = rowRepository.findById(rowDto.getId());
        if (rowOptional.isPresent()){
            Row row = rowOptional.get();
            row.setName(rowDto.getName());
            rowRepository.save(row);
        }
        else throw new EntityNotFoundException(rowDto.getId());
    }

    @Override
    public Collection<RowInfoDto> getAllRows() {
        return rowRepository.findAll().stream().map(row ->
                new RowInfoDto(row.getId(), row.getName(), row.getSector().getId())).toList();
    }

    @Override
    public RowInfoDto getRowByID(long id) throws EntityNotFoundException {
        return rowRepository.findById(id).map(row ->
                new RowInfoDto(row.getId(), row.getName(), row.getSector().getId()))
                .orElseThrow(() -> new EntityNotFoundException(id));
    }
    @Override
    public Row getRow(long id) throws EntityNotFoundException {
        return rowRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(id));
    }

    @Override
    public void deleteRowById(long id) throws EntityNotFoundException, CannotEditVenueWithExistingEventsException {
        Optional<Row> rowOptional = rowRepository.findById(id);
        if (rowOptional.isPresent()) {
            var sector = sectorService.getSector(rowOptional.get().getSector().getId());
            var venue = venueService.getVenue(sector.getVenue().getId());
            if (!venueUsageService.getEventsForVenueId(venue.getId()).isEmpty()) {
                throw new CannotEditVenueWithExistingEventsException();
            }
            rowRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException(id);
        }
    }
}
