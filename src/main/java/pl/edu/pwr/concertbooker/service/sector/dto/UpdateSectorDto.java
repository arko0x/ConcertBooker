package pl.edu.pwr.concertbooker.service.sector.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UpdateSectorDto {
    @NotNull
    private long id;
    @NotNull
    @NotEmpty
    private String sectorName;
    @NotNull
    @Min(1)
    private int numberOfRows;
    @NotNull
    private int rowInVenue;
    @NotNull
    private int columnInVenue;
}
