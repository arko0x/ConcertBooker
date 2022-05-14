package pl.edu.pwr.concertbooker.service.venue.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@Getter
@Setter
public class UpdateVenueDto {
    @NotNull
    private long Id;
    @NotNull
    @NotEmpty
    private String name;

    @NotNull
    @NotEmpty
    private String address;
}
