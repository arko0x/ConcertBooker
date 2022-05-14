package pl.edu.pwr.concertbooker.service.row.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import pl.edu.pwr.concertbooker.model.Seat;
import pl.edu.pwr.concertbooker.model.Sector;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.util.Collection;


@Getter
@Setter
@AllArgsConstructor
public class CreateRowDto {
    private String name;
    @NotNull
    private long sectorId;
}
