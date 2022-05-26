package pl.edu.pwr.concertbooker.service.interfaces;


import pl.edu.pwr.concertbooker.service.row.dto.RowInfoWithSeatsDto;

import java.util.Collection;

public interface IRowServiceUsage {
    Collection<RowInfoWithSeatsDto> getRowsBySectorId(long sectorId);
}
