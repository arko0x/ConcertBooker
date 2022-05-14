package pl.edu.pwr.concertbooker.service.venue.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import pl.edu.pwr.concertbooker.model.Sector;


import java.util.Collection;

@AllArgsConstructor
@Getter
@Setter
public class VenueInfoDto {
    private long id;
    private String name;
    private String address;
    private Collection<Sector> sectors;

    public VenueInfoDto(long id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }
}
