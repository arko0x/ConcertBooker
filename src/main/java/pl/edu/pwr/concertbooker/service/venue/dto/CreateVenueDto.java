package pl.edu.pwr.concertbooker.service.venue.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import pl.edu.pwr.concertbooker.model.Sector;

import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@AllArgsConstructor
@Getter
@Setter
public class CreateVenueDto {
    @NotNull
    @NotEmpty
    private String name;

    @NotNull
    @NotEmpty
    private String address;

}
