package pl.edu.pwr.concertbooker.service.row.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UpdateRowDto {
    @NotNull
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private Long sectorId;
}
