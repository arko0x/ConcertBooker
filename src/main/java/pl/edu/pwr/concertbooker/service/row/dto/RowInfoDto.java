package pl.edu.pwr.concertbooker.service.row.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@Getter
public class RowInfoDto {
    private long id;
    private String name;
    private long sectorId;
}
