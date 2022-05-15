package pl.edu.pwr.concertbooker.service.event.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
public class UpdateEventDto {
    @NotNull
    private long id;
    @NotNull
    @NotEmpty
    private String name;
    @NotNull
    private LocalDateTime date;
    @NotEmpty
    private String description;
    @NotNull
    @NotEmpty
    private String artist;

    @NotNull
    private long venueId;
}
